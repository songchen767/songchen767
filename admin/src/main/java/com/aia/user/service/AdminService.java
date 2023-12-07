package com.aia.user.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.resp.LoginResp;
import com.aia.system.entity.RoleEntity;
import com.aia.system.req.RoleAddReq;
import com.aia.system.req.RoleEditReq;
import com.aia.system.req.RolePageReq;
import com.aia.user.entity.AdminEntity;
import com.aia.user.req.*;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 后台管理员 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AdminService extends IService<AdminEntity> {


    /**
     * 分页查询
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<PageResp<AdminEntity>> page(AdminPageReq adminPageRequest);

    /**
     * 新增用户
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<String> add(UserAddReq userAddReq);

    /**
     * 修改用户
     *
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<String> eidt(UserEditReq UserEditReq);

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     */
    public ApiResp<String> del(Long id);


    /**
     * 用户启用/冻结
     * @author chentao
     * @param userUpdateStatusReq
     * @return
     */
    ApiResp<String> updateStatus(UserUpdateStatusReq userUpdateStatusReq);


    /**
     * 后台用户登录
     */
    public ApiResp<LoginResp> login(MemberLoginReq memberLoginReq);

    /**
     * 用户登出
     * @param request
     * @return
     */
    public ApiResp<String> logOut(HttpServletRequest request);
}
