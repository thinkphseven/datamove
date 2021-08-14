package com.longtu.datamove.controller;

import com.longtu.datamove.aspect.OpLog;
import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.service.RuleService;
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
@RequestMapping("rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @ResponseBody
    @RequestMapping("/all")
    @OpLog(module = "规则管理",opMethod = "查询数据")
    public RespEntity findAll(){
        return  ruleService.findAll();
    }

    @ResponseBody
    @RequestMapping("/findByPlanId/{planId}")
    @OpLog(module = "规则管理",opMethod = "根据方案id查询规则数据")
    public RespEntity findByPlanId(@PathVariable Long planId){
        return  ruleService.findByPlanId(planId);
    }


    @RequestMapping("/manager")
    public String  manager(){
        return "ruleList";
    }

    @RequestMapping("/add")
    public String  add(){
        return "ruleadd";
    }

    @ResponseBody
    @RequestMapping("/saveRule")
    @OpLog(module = "规则管理",opMethod = "保存数据")
    public RespEntity saveRule(@RequestBody Map map) {
        return ruleService.saveRule(map);
    }
    @RequestMapping(value = "/delRule/{id}", method = RequestMethod.POST)
    @ResponseBody
    @OpLog(module = "规则管理",opMethod = "删除数据")
    public  RespEntity delRule(@PathVariable("id") Long id) {
        return ruleService.delRuleById(id);
    }

    @ResponseBody
    @RequestMapping("/execute")
    @OpLog(module = "规则管理",opMethod = "执行规则")
    public RespEntity execute(@RequestBody Long [] rules) throws SQLException {
        return ruleService.execute(rules);
    }

    @ResponseBody
    @GetMapping("/selectfromtable/{planId}")
    @OpLog(module = "规则管理",opMethod = "查询来源库所有表")
    public RespEntity selectFromTable(@PathVariable Long planId) throws SQLException {
        return ruleService.selectTableByPlanId(planId);
    }
    @ResponseBody
    @GetMapping("/selecttotable/{planId}")
    @OpLog(module = "规则管理",opMethod = "查询目标库所有表")
    public RespEntity selectToTable(@PathVariable Long planId) throws SQLException {
        return ruleService.selectToTable(planId);
    }
    @ResponseBody
    @GetMapping("/selectColumnByTable/{planId}/{tablename}/{value}")
    public RespEntity selectColumnByTable(@PathVariable Long planId,@PathVariable String tablename,@PathVariable String value) throws SQLException {
        return ruleService.selectColumnByTable(planId,tablename,value);
    }
    @ResponseBody
    @RequestMapping("/copyRule")
    @OpLog(module = "方案管理",opMethod = "方案复制")
    public RespEntity copyRule(@RequestBody List<Rule> rules) {
        return ruleService.copyRule(rules);
    }

    @ResponseBody
    @RequestMapping("/executeRule")
    @OpLog(module = "规则管理",opMethod = "执行规则")
    public RespEntity executeRule(Rule rule) throws SQLException {
        return ruleService.executeRule(rule);
    }

    @ResponseBody
    @RequestMapping("/savetoRule")
    @OpLog(module = "规则管理",opMethod = "保存数据")
    public RespEntity savetoRule(@RequestBody Map map) {
        return ruleService.savetoRule(map);
    }
}
