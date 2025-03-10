package com.elon.hypesphere.member.service.impl;

import com.elon.hypesphere.member.entity.Member;
import com.elon.hypesphere.member.mapper.MemberMapper;
import com.elon.hypesphere.member.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
