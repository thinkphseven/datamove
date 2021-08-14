package com.longtu.datamove.service;

import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.resp.RespEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RuleService {
    RespEntity findAll();

    RespEntity saveRule(Map map);

    RespEntity delRuleById(Long id);

    RespEntity execute(Long[] rules) throws SQLException;

    RespEntity selectTableByPlanId(Long planId);

    RespEntity selectToTable(Long planId);

    RespEntity selectColumnByTable(Long planId, String tablename, String value);

    RespEntity copyRule(List<Rule> rules);

    RespEntity findByPlanId(Long planId);

    RespEntity executeRule(Rule rule) throws SQLException;

    RespEntity savetoRule(Map map);
}
