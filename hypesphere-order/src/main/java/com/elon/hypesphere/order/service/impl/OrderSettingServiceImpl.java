package com.elon.hypesphere.order.service.impl;

import com.elon.hypesphere.order.entity.OrderSetting;
import com.elon.hypesphere.order.mapper.OrderSettingMapper;
import com.elon.hypesphere.order.service.IOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单配置信息 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements IOrderSettingService {

}
