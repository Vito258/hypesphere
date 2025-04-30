package com.elon.hypesphere.product.web;

import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.service.ICategoryService;
import com.elon.hypesphere.product.vo.Catalog2Vo;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class IndexController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping({"/", "index.html"})
    public String indexPage(Model model) {
        // 1、查出所有1级分类
        List<Category> categories = categoryService.getLevel1Categories();

        model.addAttribute("categories", categories);
        return "index";
    }

    @ResponseBody
    @RequestMapping("/index/catalog.json")
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        return categoryService.getCatalogJson();
    }

    /**
     * 测试Redisson分布式锁
     * @return
     */
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        // 1、 获取一把锁，只要锁的名字一样，就是同一把锁
        redissonClient.getLock("my-lock").lock();

        // 2、 锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务时间长，锁自动过期被删掉
        try {
            // 3、 获取到锁以后，执行业务逻辑
            System.out.println(STR."业务执行。。。。\{Thread.currentThread().getName()}");
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 4、 释放锁
            redissonClient.getLock("my-lock").unlock();
        }
        return "hello";
    }

    /**
     * 测试Redisson 读写锁-写
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/write")
    public String writeValue() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        // 写数据加写锁
        redissonClient.getReadWriteLock("rw-lock").writeLock().lock();
        try {
            System.out.println("写入数据");
            stringRedisTemplate.opsForValue().set("read-write-value", uuid);
            Thread.sleep(30000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            // 解锁
            redissonClient.getReadWriteLock("rw-lock").writeLock().unlock();
        }
        return STR."写入成功：\{uuid}";
    }

    /**
     * 测试Redisson 读写锁-读
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/read")
    public String readValue() throws InterruptedException {
        // 读数据加读锁
        redissonClient.getReadWriteLock("rw-lock").readLock().lock();
        String value = "";
        try{
            value = stringRedisTemplate.opsForValue().get("read-write-value");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 解锁
           redissonClient.getReadWriteLock("rw-lock").readLock().unlock();
        }
        return STR."读取成功\{value}";
    }

    /**
     * 闭锁测试
     */
    @ResponseBody
    @RequestMapping("/lockdoor")
    public String lockdoor() throws InterruptedException {
        // 闭锁
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("door");
        countDownLatch.trySetCount(5);
        // 等待闭锁都完成
        countDownLatch.await();
        return "锁门成功";
    }

    @ResponseBody
    @RequestMapping("/godoor/{id}")
    public String godoor(@PathVariable("id") Long id) throws InterruptedException {
        // 闭锁
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("door");
        // 计数减1
        countDownLatch.countDown();
        return "开锁成功";
    }

    /**
     * 信号量测试
     *
     */
    @ResponseBody
    @RequestMapping("/park")
    public String park() throws InterruptedException {
        // 信号量
        RSemaphore semaphore = redissonClient.getSemaphore("park");
        // 获取一个值（占一个车位）
        // 阻塞式获取，如果没有车位就等待，除此之外还有tryAcquire，如果没有车位就算了
        semaphore.acquire();
        return "占一个车位";
    }

    @ResponseBody
    @RequestMapping("/go")
    public String go() throws InterruptedException {
        // 信号量
        RSemaphore semaphore = redissonClient.getSemaphore("park");
        // 释放一个值（释放一个车位）
        semaphore.release();
        return "释放一个车位";
    }
}
