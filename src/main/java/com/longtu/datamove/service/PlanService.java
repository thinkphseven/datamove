package com.longtu.datamove.service;

import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.resp.RespEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * @Title: PlanService
 * @description:
 * @author: hk
 * @date: 2021-05-13 10:02
 **/
public interface PlanService {
    RespEntity findAll();

    RespEntity execute(Long[] plans) throws SQLException;

    RespEntity savePlan(Plan plan);

    Plan getPlanById(Long id);

    RespEntity delPlanById(Long id);

    RespEntity startOrStopPlan(List<Plan> plans, String s);

    RespEntity copyPlan(List<Plan> plans);

}
