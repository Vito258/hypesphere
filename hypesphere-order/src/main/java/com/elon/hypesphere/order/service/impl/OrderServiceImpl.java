package com.elon.hypesphere.order.service.impl;

import com.elon.hypesphere.order.entity.Order;
import com.elon.hypesphere.order.mapper.OrderMapper;
import com.elon.hypesphere.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
