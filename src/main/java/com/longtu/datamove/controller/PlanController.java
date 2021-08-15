package com.longtu.datamove.controller;

import com.longtu.datamove.aspect.OpLog;
import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.service.PlanAcctColumnService;
import com.longtu.datamove.service.PlanAcctService;
import com.longtu.datamove.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Title: PlanController
 * @description:
 * @author: hk
 * @date: 2021-04-28 15:35
 **/
@Controller
@RequestMapping("plan")
public class PlanController {

    @Autowired
    private PlanService planService;
    @Autowired
    private PlanAcctService planAcctService;
    @Autowired
    private PlanAcctColumnService planAcctColumnService;

    @ResponseBody
    @RequestMapping("/all")
    @OpLog(module = "方案管理",opMethod = "查询数据")
    public RespEntity findAll() {
        return planService.findAll();
    }

    @RequestMapping("/manager")
    public String manager() {
        return "planList";
    }

    @RequestMapping("/add")
    public String add() {
        return "planadd";
    }
    @RequestMapping("/acct")
    public String acct() {
        return "acctList";
    }

    @ResponseBody
    @RequestMapping("/savePlan")
    @OpLog(module = "方案管理",opMethod = "保存数据")
    public RespEntity savePlan(Plan plan) {
        return planService.savePlan(plan);
    }


    @RequestMapping(value = "/delPlan/{id}", method = RequestMethod.POST)
    @ResponseBody
    @OpLog(module = "方案管理",opMethod = "删除数据")
    public  RespEntity delPlan(@PathVariable("id") Long id) {
        return planService.delPlanById(id);
    }

    @ResponseBody
    @RequestMapping("/execute")
    @OpLog(module = "方案管理",opMethod = "执行方案")
    public RespEntity execute(@RequestBody Long [] plans) throws SQLException {
        return planService.execute(plans);
    }

    @ResponseBody
    @RequestMapping("/startPlan")
    @OpLog(module = "方案管理",opMethod = "启用方案")
    public RespEntity startPlan(@RequestBody List<Plan> plans) {
        return planService.startOrStopPlan(plans, "1");
    }

    @ResponseBody
    @RequestMapping("/stopPlan")
    @OpLog(module = "方案管理",opMethod = "停用方案")
    public RespEntity stopPlan(@RequestBody List<Plan> plans) {
        return planService.startOrStopPlan(plans, "0");
    }
    @ResponseBody
    @RequestMapping("/copyPlan")
    @OpLog(module = "方案管理",opMethod = "方案复制")
    public RespEntity copyPlan(@RequestBody List<Plan> plans) {
        return planService.copyPlan(plans);
    }



    @ResponseBody
    @GetMapping("/selectAcctColumn/{acctId}/{type}")
    @OpLog(module = "对应关系",opMethod = "对应关系查询")
    public RespEntity selectAcctColumn(@PathVariable("acctId") Long acctId,@PathVariable("type") String type) {
        return planAcctColumnService.selectAcctColumn(acctId,type);
    }

    @ResponseBody
    @RequestMapping("/savetoAcct")
    @OpLog(module = "方案管理",opMethod = "保存辅助方案数据")
    public RespEntity savetoAcct(@RequestBody Map map) {
        return planAcctService.savetoAcct(map);
    }

    @ResponseBody
    @RequestMapping("/acctAll")
    @OpLog(module = "方案管理",opMethod = "辅助方案数据")
    public RespEntity acctAll() {
        return planAcctService.findAll();
    }


}
