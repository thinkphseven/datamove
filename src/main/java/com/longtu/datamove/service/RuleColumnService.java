package com.longtu.datamove.service;

import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.entity.RuleColumn;
import com.longtu.datamove.repositiory.PlanRepositiory;
import com.longtu.datamove.repositiory.RuleColumnRepositiory;
import com.longtu.datamove.resp.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleColumnService{

    @Autowired
    private RuleColumnRepositiory ruleColumnRepositiory;

    @Autowired
    private PlanRepositiory planRepositiory;

    public RespEntity selectRuleColumn(Rule rule) {
        List<RuleColumn> list = ruleColumnRepositiory.selectRuleColumn(rule.getId());
        if(list ==null || list.size()<1){
            String sourceSql = rule.getSourceSql();
            String targetSql = rule.getTargetSql();
            if(sourceSql!=null){
                Long planId = rule.getPlanId();
                //获取方案的数据库连接信息
                Plan plan = planRepositiory.findById(planId).get();
//                List<String> columns = SelectSQL.selectSQLtoDb(sourceSql,plan);
                String sql = sourceSql.toLowerCase();//转成小写
                String sql2 = sql.split("select")[1];
                String sql3 = sql2.split("from")[0];
                String [] columns = sql3.split(",");

                int begin = targetSql.indexOf("(");
                int last = targetSql.indexOf(")");
                String str = targetSql.substring(begin+1,last);
                String[] split = str.split(",");

                for(int i=0;i<columns.length;i++){
                    RuleColumn ruleColumn = new RuleColumn();
                    ruleColumn.setRuleId(rule.getId());
                    ruleColumn.setSourceField(columns[i]);
                    ruleColumn.setTargetField(split[i]);
                    list.add(ruleColumn);
                }
            }
        }
        return new RespEntity("0", list, "查询成功");
    }
}
