package com.longtu.datamove.service.impl;

import com.longtu.datamove.entity.PlanAcctColumn;
import com.longtu.datamove.repositiory.PlanAcctColumnRepositiory;
import com.longtu.datamove.resp.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanAcctColumnService {

    @Autowired
    private PlanAcctColumnRepositiory planAcctColumnRepositiory;

    public RespEntity selectAcctColumn(Long acctId,String type) {
        List<PlanAcctColumn> list = new ArrayList<>();
        if("0".equals(type)){
            list = planAcctColumnRepositiory.selectAcctColumnByType(type);
        }else {
            list = planAcctColumnRepositiory.selectAcctColumnByAcctid(acctId);
        }

        return  new RespEntity("0",list,"查询成功");
    }
}
