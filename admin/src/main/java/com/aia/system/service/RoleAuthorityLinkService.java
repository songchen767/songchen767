package com.aia.system.service;

import com.aia.base.resp.ApiResp;
import com.aia.system.entity.RoleAuthorityLinkEntity;
import com.aia.system.req.RoleAuthAddReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface RoleAuthorityLinkService extends IService<RoleAuthorityLinkEntity> {

    /**
     * @author chentao
     * 角色更新权限
     */
    public ApiResp<String> assignment(RoleAuthAddReq roleAuthAddReq);

    /**
     * @author chentao
     * 角色新增权限
     *
     */
    public void add(List<RoleAuthorityLinkEntity> list);

    /**
     * @author chentao
     * 角色批量删除权限
     */
    public void batchDelByAuthId(List<Long> authIds);
}
