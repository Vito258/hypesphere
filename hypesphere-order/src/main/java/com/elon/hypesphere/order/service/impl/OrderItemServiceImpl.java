package com.elon.hypesphere.order.service.impl;

import com.elon.hypesphere.order.entity.OrderItem;
import com.elon.hypesphere.order.mapper.OrderItemMapper;
import com.elon.hypesphere.order.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单项信息 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
