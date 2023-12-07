package com.aia.finance.service;

import com.aia.finance.entity.AgentWalletEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 代理钱包管理 服务类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
public interface AgentWalletService extends IService<AgentWalletEntity> {

    /**
     * 根据代理id获取对象
     */
    AgentWalletEntity getByAgentId(Long id);

}
