package com.aia.user.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.resp.LoginResp;
import com.aia.user.entity.MemberEntity;
import com.aia.user.req.*;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员信息 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface MemberService extends IService<MemberEntity> {

    /**
     * 会员分页查询
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<PageResp<MemberEntity>> page(MemberPageReq memberPageReq);


    /**
     * 更新会员状态
     * @param memberUpdateStatusReq
     * @return
     */
    public ApiResp<String> updateStatus(MemberUpdateStatusReq memberUpdateStatusReq);

    /**
     * 所属代理转移
     * @param memberTransferAgentReq
     * @return
     */
    public ApiResp<String> transferMember(MemberTransferAgentReq memberTransferAgentReq);

    /**
     * 所属会员转移
     * @param memberTransferAgentReq
     * @return
     */
    ApiResp<String> transferMemberForParent(MemberTransferParentReq memberTransferAgentReq);

    /**
     * 批量更新会员状态
     * @param agentId
     * @param status
     * @return
     */
    ApiResp<String> updateStatusByAgentId(Long agentId,Integer status);

    /**
     * 会员注册
     */
    ApiResp<String> registe(MemberRegisteReq memberRegisteReq);


    /**
     * 分页查询下级会员
     * @param memberForAgentPageReq
     * @return
     */
    ApiResp<PageResp<MemberEntity>> childMemberPage(MemberForAgentPageReq memberForAgentPageReq);

    /**
     * 修改密码
     * @param memberUpdatePasswordReq
     * @return
     */
    ApiResp<String> updatePassword(MemberUpdatePasswordReq memberUpdatePasswordReq);

    ApiResp<LoginResp> login(MemberLoginReq memberLoginReq);
}
