package com.longtu.datamove.service;

import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.resp.RespEntity;

public interface RuleColumnService {

    RespEntity selectRuleColumn(Rule rule);
}
