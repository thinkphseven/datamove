package com.longtu.datamove.service.impl;

import com.longtu.datamove.entity.Note;
import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.repositiory.NoteRepositiory;
import com.longtu.datamove.repositiory.PlanRepositiory;
import com.longtu.datamove.repositiory.RuleRepositiory;
import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.service.NoteService;
import com.longtu.datamove.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: NoteServiceImpl
 * @description:
 * @author: hk
 * @date: 2021-05-13 10:03
 **/
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepositiory noteRepositiory;

    @Autowired
    private PlanRepositiory planRepository;


    @Override
    public RespEntity findAll() {
        return new RespEntity ("0", noteRepositiory.findAll(),"查询成功");
    }

    @Override
    public void saveNote(String module, String opMethod, String s) {
        Note note = new Note();
        note.setIsDelete(0);
        note.setModule(module);
        note.setOpMethod(opMethod);
        note.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        note.setResult(s);
        note.setUserName("admin");
        noteRepositiory.save(note);
    }
}
