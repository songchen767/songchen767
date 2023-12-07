package com.aia.system.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.system.entity.AuthorityEntity;
import com.aia.system.entity.RoleEntity;
import com.aia.system.req.*;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台角色 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface RoleService extends IService<RoleEntity> {

    /**
     * 角色分页查询
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<PageResp<RoleEntity>> page(RolePageReq rolePageReq);

    /**
     * 新增角色
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<String> add(RoleAddReq roleAddReq);

    /**
     * 修改角色
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<String> eidt(RoleEditReq roleEditReq);

    /**
     * 删除角色
     *
     * @param id
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<String> del(Long id);

    /**
     *
     *  角色启/停用
     * @author chentao
     *
     * @param roleStatusEditReq
     * @return
     */
    public ApiResp<String> updateStatus(RoleStatusEditReq roleStatusEditReq);

}
