package com.elon.hypesphere.search.thread;

import java.util.concurrent.*;

public class ThreadTest {

    // 创建线程池
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);



    //1 继承Thread类
    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread01 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("i = " + i);
        }
    }

    // 2 实现Runnable接口
    public static class Runable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("Runable01 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("i = " + i);
        }
    }

    // 3 实现Callable接口
    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Callable01 当前线程：" + Thread.currentThread().getName());
            return 10 / 2;
        }
    }

    // 线程创建方式测试
    public static void threadTest(String[] args) {

        // 1、继承thread
        Thread01 thread01 = new Thread01();
        thread01.start();  // 启动线程

        // 2、继承Runnable
        Runable01 runable01 = new Runable01();
        new Thread(runable01).start();

        // 3、继承callable
        try {
            Callable01 callable01 = new Callable01();
            FutureTask<Integer> integerFutureTask = new FutureTask<>(callable01);
            new Thread(integerFutureTask).start();
            System.out.println("callable01 结果 i = " + integerFutureTask.get());  // 得到结果
        } catch (Exception e) {
            System.out.println("main 方法异常 = " + e);    // 处理异常
        }

        // 4、创建线程池
        // 4.1 Executor
        executorService.execute(new Runable01());
        // 4.2 原生的方式
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,                                  // 线程池中核心线程数量
                200,                                           // 线程池中线程最大数量
                10, TimeUnit.SECONDS,                              // 线程池中线程空闲后的最大存活时间
                new LinkedBlockingDeque<>(100000),     // 阻塞队列
                new ThreadPoolExecutor.AbortPolicy()           // 丢弃策略
        );
        System.out.println("main 方法结束");
    }

    // CompletableFuture任务测试
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建CompletableFuture,参数分别为任务和自定义线程池
        // 1、 runAsync
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            // 线程任务
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("i = " + i);
        }, executorService);

        // 2、 supplyAsync
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
                    System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
                    int i = 10 / 0;
                    return i;
                }, executorService)
                // 2.1 whenComplete 得到执行结果，进行方法完成之后的感知
                .whenComplete((res,exception) -> {
                    System.out.println("异步任务已完成，结果是"+res+"，异常："+ exception);
                })
                // 2.2 使用 exceptionally 可以感知异常，同时修改并返回默认值
                .exceptionally(t -> 10);

        // 3、handle 感知并处理结果，进行方法完成之后的处理
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
                    System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
                    int i = 10 / 0;
                    return i;
                }, executorService)
                .handle((res,exception) -> {
                    System.out.println("异步任务已完成，结果是"+res+"，异常："+ exception);
                    // 3.1 方法成功
                    if(res != null){
                        return res * 2;
                    }
                    // 3.2 方法失败
                    if(exception != null){
                        return 0;
                    }
                    return res;
                });

        // 4、线程串行化
        // 4.1 thenRun：不能获取到上一步的执行结果
        CompletableFuture<Void> future4_1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("当前结果 i = " + i);
            return i;
        },executorService).thenRunAsync(() -> {
            System.out.println("thenRun任务启动了");
        },executorService);

        // 4.2 thenAccept： 能获取上一步的执行结果,但是无返回值
        CompletableFuture<Void> future4_2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("当前结果 i = " + i);
            return i;
        },executorService).thenAcceptAsync(res -> {
            System.out.println("thenAccept启动了");
            System.out.println("thenAccept上一步结果是：" + res);
        },executorService);

        // 4.3 thenApply：能获取上步的执行结果，有返回值
        CompletableFuture<Integer> future4_3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("当前结果 i = " + i);
            return i;
        },executorService).thenApplyAsync(res -> {
            System.out.println("thenApply启动了");
            System.out.println("thenApply上步结果是：" + res);
            return res * 10;
        },executorService);

        // 使用get() 获取结果
        System.out.println("======================================================");
        System.out.println("future1.get() = " + future1.get());
        System.out.println("future2.get() = " + future2.get());
        System.out.println("future3.get() = " + future3.get());
        System.out.println("future4_1.get() = " + future4_1.get());
        System.out.println("future4_2.get() = " + future4_2.get());
        System.out.println("future4_3.get() = " + future4_3.get());

        // 5、线程组合
        // 创建三个线程任务
        CompletableFuture<Integer> future5_1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("任务5-1 当前结果 i = " + i);
            return i;
        }, executorService);

        CompletableFuture<Integer> future5_2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 3;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务5-2 当前结果 i = " + i);
            return i;
        }, executorService);

        CompletableFuture<Integer> future5_3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 当前线程：" + Thread.currentThread().getName());
            int i = 10 / 5;
            System.out.println("任务5-3 当前结果 i = " + i);
            return i;
        }, executorService);

        // 5.1 runAfterBoth 两个都执行完成，执行后续任务
        future5_1.runAfterBothAsync(future5_2,() -> {
            System.out.println("runAfterBothAsync: 任务5-1和任务5-2都执行完成, 后续任务执行...");
        }, executorService);

        // 5.2 thenAcceptBoth 两个都执行完成，获取两个结果，执行后续任务，但无返回值
        future5_1.thenAcceptBothAsync(future5_2, (res1,res2) -> {
            System.out.println("thenAcceptBothAsync: 任务5-1和任务5-2都执行完成, 获取结果：" + res1 + "，" + res2);
        }, executorService);

        // 5.3 thenCombine 两个都执行完成，获取两个结果，执行后续任务，有返回值
        CompletableFuture<Integer> integerCompletableFuture = future5_1.thenCombineAsync(future5_2, (res1, res2) -> {
            System.out.println("thenCombine: 任务5-1和任务5-2都执行完成, 获取结果：" + res1 + "，" + res2);
            System.out.println("thenCombine: 获取结果：" + res1 + "，" + res2);
            return res1 + res2;
        }, executorService);

        // 5.4 runAfterEither：两任务组合-1个完成，不获取它的返回值，执行后续任务，也没有新的返回值
        future5_1.runAfterEitherAsync(future5_2, () -> {
            System.out.println("runAfterEither: 任务5-1和任务5-2有一个执行完成, 后续任务执行");
        },executorService);

        // 5.5 acceptEither：两任务组合-1个完成，获取它的返回值，执行后续任务，没有新的返回值
        future5_1.acceptEitherAsync(future5_2, (res) -> {
            System.out.println("acceptEither: 任务5-1和任务5-2有一个执行完成, 获取结果：" + res);
        },executorService);

        // 5.6 applyToEither：两任务组合-1个完成，获取它的返回值，执行后续任务，有新的返回值
        future5_1.applyToEitherAsync(future5_2, (res) -> {
            System.out.println("applyToEither: 任务5-1和任务5-2有一个执行完成, 获取结果：" + res);
            return res * 10;
        },executorService);

        // 6 多任务组合
        // 6.1 allof ：多个任务都执行完成，不获取它的返回值，执行后续任务，没有返回值
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future5_1, future5_2, future5_3);
        // 等待所有任务执行完成
//        allOf.get();
        System.out.println("allOf: 6.1 allOf 任务执行完成");

        // 6.2 anyof ：多个任务有一个执行完成，拿到其执行结果，执行后续任务，没有返回值
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future5_1, future5_2, future5_3);
        System.out.println("anyOf: 6.2 anyOf 任务执行完成，结果是：" + anyOf.get());
    }
}
