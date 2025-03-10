package com.elon.hypesphere.coupon.service.impl;

import com.elon.hypesphere.coupon.entity.HomeSubject;
import com.elon.hypesphere.coupon.mapper.HomeSubjectMapper;
import com.elon.hypesphere.coupon.service.IHomeSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectMapper, HomeSubject> implements IHomeSubjectService {

}
