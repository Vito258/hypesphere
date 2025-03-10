package com.elon.hypesphere.ware.service.impl;

import com.elon.hypesphere.ware.entity.Log;
import com.elon.hypesphere.ware.mapper.LogMapper;
import com.elon.hypesphere.ware.service.ILogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
