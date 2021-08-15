package com.longtu.datamove.service;

import com.longtu.datamove.entity.Note;
import com.longtu.datamove.repositiory.NoteRepositiory;
import com.longtu.datamove.repositiory.PlanRepositiory;
import com.longtu.datamove.resp.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class NoteService{

    @Autowired
    private NoteRepositiory noteRepositiory;

    @Autowired
    private PlanRepositiory planRepository;


    public RespEntity findAll() {
        return new RespEntity ("0", noteRepositiory.findAll(),"查询成功");
    }

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
