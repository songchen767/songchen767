package com.aia.finance.service.impl;

import com.aia.finance.entity.AgentWalletEntity;
import com.aia.finance.mapper.AgentWalletMapper;
import com.aia.finance.service.AgentWalletService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代理钱包管理 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class AgentWalletServiceImpl extends ServiceImpl<AgentWalletMapper, AgentWalletEntity> implements AgentWalletService {

    @Override
    public AgentWalletEntity getByAgentId(Long id) {
        LambdaQueryWrapper<AgentWalletEntity> lambdaQueryWrapper = new QueryWrapper<AgentWalletEntity>().lambda();
        lambdaQueryWrapper.eq(AgentWalletEntity::getAgentId, id);
        return getOne(lambdaQueryWrapper);
    }
}
