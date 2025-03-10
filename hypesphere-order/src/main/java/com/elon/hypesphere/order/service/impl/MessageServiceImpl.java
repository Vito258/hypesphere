package com.elon.hypesphere.order.service.impl;

import com.elon.hypesphere.order.entity.Message;
import com.elon.hypesphere.order.mapper.MessageMapper;
import com.elon.hypesphere.order.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
