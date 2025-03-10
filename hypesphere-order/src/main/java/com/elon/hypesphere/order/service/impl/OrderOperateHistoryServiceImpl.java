package com.elon.hypesphere.order.service.impl;

import com.elon.hypesphere.order.entity.OrderOperateHistory;
import com.elon.hypesphere.order.mapper.OrderOperateHistoryMapper;
import com.elon.hypesphere.order.service.IOrderOperateHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单操作历史记录 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory> implements IOrderOperateHistoryService {

}
