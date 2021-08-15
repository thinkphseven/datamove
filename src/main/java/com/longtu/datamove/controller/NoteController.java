package com.longtu.datamove.controller;

import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ResponseBody
    @RequestMapping("/all")
    public RespEntity find(){
        return  noteService.findAll();
    }

    @RequestMapping("/manager")
    public String manager() {
        return "note/noteManage";
    }

    @RequestMapping("/finish")
    public String finish() {
        return "finish";
    }

}
