package com.aia.msg.service.impl;

import org.springframework.stereotype.Service;

import com.aia.msg.entity.MessageEntity;
import com.aia.msg.mapper.MessageMapper;
import com.aia.msg.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 会员个人消息 服务实现类
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements MessageService {

}
