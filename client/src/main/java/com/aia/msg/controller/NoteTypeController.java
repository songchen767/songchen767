package com.aia.msg.controller;

import com.aia.annotations.HasLogin;
import com.aia.base.resp.ApiResp;
import com.aia.msg.entity.NoteTypeEntity;
import com.aia.msg.service.NoteTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 公告类型管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/msg/noteType")
public class NoteTypeController {

    @Autowired
    private NoteTypeService noteTypeService;


    @HasLogin
    @GetMapping("/queryNoteType")
    @ApiOperation("查询有效的公告类型")
    public ApiResp<List<NoteTypeEntity>> queryNoteType(){
        return noteTypeService.queryNoteType();
    }

}
