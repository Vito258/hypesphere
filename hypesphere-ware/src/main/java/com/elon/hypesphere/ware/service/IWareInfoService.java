package com.elon.hypesphere.ware.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.ware.entity.WareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IWareInfoService extends IService<WareInfo> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);
}
