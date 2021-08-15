package com.longtu.datamove.controller;

import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.service.RuleColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


@Controller
@RequestMapping("rulecolumn")
public class RuleColumnController {

    @Autowired
    private RuleColumnService ruleColumnService;

    @ResponseBody
    @PostMapping("/selectRuleColumn")
    public RespEntity selectRuleColumnOne(Rule rule) throws SQLException {
        return ruleColumnService.selectRuleColumn(rule);
    }
}