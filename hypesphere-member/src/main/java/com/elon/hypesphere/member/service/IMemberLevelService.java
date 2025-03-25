package com.elon.hypesphere.member.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.member.entity.MemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 会员等级 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IMemberLevelService extends IService<MemberLevel> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);
}
