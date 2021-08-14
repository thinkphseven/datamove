package com.longtu.datamove.service;

import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.resp.RespEntity;

/**
 * @Title: PlanService
 * @description:
 * @author: hk
 * @date: 2021-05-13 10:02
 **/
public interface NoteService {
    RespEntity findAll();

    void saveNote(String module, String opMethod, String s);
}
