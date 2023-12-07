package com.aia.system.service;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.system.entity.AuthorityEntity;
import com.aia.system.req.AuthorithEditReq;
import com.aia.system.req.AuthorityAddReq;
import com.aia.system.req.AuthorityPageReq;
import com.aia.system.req.AuthorityStatusEditReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台权限 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AuthorityService extends IService<AuthorityEntity> {

    /**
     * 分页查询示例
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     * @return
     */
    public ApiResp<PageResp<AuthorityEntity>> page(AuthorityPageReq authorityPageReq);

    /**
     * 新增权限
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     * @return
     */
    public ApiResp<String> add(AuthorityAddReq authorityAddReq);

    /**
     * 修改权限
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     * @return
     */
    public ApiResp<String> eidt(AuthorithEditReq authorithEditReq);

    /**
     * 删除权限
     * @author chentao
     * @version 创建时间：2023/11/9 17:00
     * @param id
     * @return
     */
    public ApiResp<String> del(Long id);


    /**
     * 权限启/停用
     * @author chentao
     * @param authorityStatusEditReq
     * @return
     */
    ApiResp<String> updateStatus(AuthorityStatusEditReq authorityStatusEditReq);
}
