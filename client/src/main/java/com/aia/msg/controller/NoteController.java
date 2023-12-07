package com.aia.msg.controller;

import com.aia.base.resp.ApiResp;
import com.aia.base.resp.PageResp;
import com.aia.msg.entity.NoteEntity;
import com.aia.msg.req.NoteListReq;
import com.aia.msg.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 公告管理 前端控制器
 * </p>
 *
 * @author yangxy
 * @since 2023-11-03
 */
@RestController
@RequestMapping("/msg/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/queryNoteByType")
    @ApiOperation(value = "分页查询公告")
    public ApiResp<PageResp<NoteEntity>> queryNoteByType(NoteListReq noteListReq){
        return noteService.queryNoteByType(noteListReq);
    }


    @GetMapping("/queryNoteDetail/{id}")
    @ApiOperation(value = "公告详情")
    public ApiResp<NoteEntity> queryNoteDetail(@PathVariable("id") Long id){
        return noteService.queryNoteDetail(id);
    }




}
