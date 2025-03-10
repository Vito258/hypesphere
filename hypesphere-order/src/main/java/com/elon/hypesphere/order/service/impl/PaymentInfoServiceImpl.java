package com.elon.hypesphere.order.service.impl;

import com.elon.hypesphere.order.entity.PaymentInfo;
import com.elon.hypesphere.order.mapper.PaymentInfoMapper;
import com.elon.hypesphere.order.service.IPaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付信息表 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {

}
