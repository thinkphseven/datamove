package com.longtu.datamove.service;

import com.alibaba.fastjson.JSON;
import com.longtu.datamove.entity.PlanAcct;
import com.longtu.datamove.entity.PlanAcctColumn;
import com.longtu.datamove.repositiory.PlanAcctColumnRepositiory;
import com.longtu.datamove.repositiory.PlanAcctRepositiory;
import com.longtu.datamove.resp.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlanAcctService{

    @Autowired
    private PlanAcctRepositiory planAcctRepositiory;
    @Autowired
    private PlanAcctColumnRepositiory planAcctColumnRepositiory;

    public RespEntity savetoAcct(Map map) {
        String json = JSON.toJSON(map.get("acct")).toString();
        PlanAcct planAcct = JSON.parseObject(json, PlanAcct.class);
        planAcctRepositiory.save(planAcct);

        List<PlanAcctColumn> dataForm = (List<PlanAcctColumn>) map.get("dataForm");
        List<PlanAcctColumn> lists = new ArrayList<>();
        for(int i = 0;i<dataForm.size();i++){
            PlanAcctColumn planAcctColumn = JSON.parseObject(JSON.toJSONString(dataForm.get(i)), PlanAcctColumn.class);
            planAcctColumn.setAcctId(planAcct.getId());
            planAcctColumn.setType("1");
            lists.add(planAcctColumn);
        }
        planAcctColumnRepositiory.saveAll(lists);
        return new RespEntity("0","保存辅助方案成功");
    }

    public RespEntity findAll() {
        List<PlanAcct> planAcctList = planAcctRepositiory.findAll();
        return new RespEntity("0",planAcctList,"查询辅助方案成功");
    }
}
