package com.longtu.datamove.service;

import com.alibaba.fastjson.JSON;
import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.entity.RuleColumn;
import com.longtu.datamove.repositiory.PlanRepositiory;
import com.longtu.datamove.repositiory.RuleColumnRepositiory;
import com.longtu.datamove.repositiory.RuleRepositiory;
import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.strategy.ConnectDB;
import com.longtu.datamove.strategy.StrategySimpleFactory;
import com.longtu.datamove.util.SelectSQL;
import com.longtu.datamove.util.SelectTableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title: RuleServiceImpl
 * @description:
 * @author: hk
 * @date: 2021-05-13 10:03
 **/
@Service
public class RuleService{

    @Autowired
    private RuleRepositiory ruleRepositiory;

    @Autowired
    private PlanRepositiory planRepository;

    @Autowired
    private RuleColumnRepositiory ruleColumnRepositiory;

    
    public RespEntity findAll() {
        return new RespEntity("0",ruleRepositiory.findAll(Sort.by("planId","orderNum")),"查询数据成功");
    }


    
    @Transactional
    public RespEntity saveRule(Map map) {
        String json = JSON.toJSON(map.get("rule")).toString();
        Rule rule = JSON.parseObject(json, Rule.class);
        List<RuleColumn> dataForm = (List<RuleColumn>) map.get("dataForm");
        List<RuleColumn> lists = new ArrayList<>();
        for(int i = 0;i<dataForm.size();i++){
            RuleColumn ruleColumn = JSON.parseObject(JSON.toJSONString(dataForm.get(i)), RuleColumn.class);
            ruleColumn.setRuleId(rule.getId());
            ruleColumn.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            lists.add(ruleColumn);
        }
        ruleColumnRepositiory.saveAll(lists);
        return new RespEntity("0","规则保存成功");
    }

    
    @Transactional
    public RespEntity delRuleById(Long id) {
         ruleRepositiory.deleteById(id);
        ruleColumnRepositiory.deleteByRuleId(id);
        return new RespEntity("0","规则删除成功");
    }

    
    @Transactional
    public RespEntity execute(Long [] rules) throws SQLException {
        boolean flag = true;
        //每个规则逐步执行  查询每个规则的sql
        List<Long> params = new ArrayList<>();
        for (Long l : rules) {
            params.add(l);
        }
        List<Rule> list = ruleRepositiory.findAllById(params);
        list = list.stream().sorted((i, j) -> i.getOrderNum() - j.getOrderNum()).collect(Collectors.toList());
        for (Rule obj : list) {
            //获取每个规则的sql
            String sourcesql = obj.getSourceSql();
            String targetSql= obj.getTargetSql();
            Long planId = obj.getPlanId();
            //获取方案的数据库连接信息
            Plan plan = planRepository.findById(planId).get();
            if (sourcesql == null || "".equals(sourcesql)) {
                //直接执行目标sql
                // 插入目标库
                ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getTargetDbType());
                 flag = connectDB.execute(connectDB.getConnection( plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(),plan.getTargetIp(), plan.getTargetDb()),targetSql);
            } else {
                //执行来源sql查出来结果集
                ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getSourceDbType());
                List<Map<String, Object>> datas = connectDB.query(connectDB.getConnection( plan.getSourceUsername(), plan.getSourcePwd(), plan.getSourcePort(),plan.getSourceIp(), plan.getSourceDb()), sourcesql);
                if (datas != null && datas.size() > 0) {
                    // 插入目标库
                    connectDB = StrategySimpleFactory.getInstance(plan.getTargetDbType());
                    flag = connectDB.insertBatch(connectDB.getConnection( plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(),plan.getTargetIp(), plan.getTargetDb()),datas,obj);
                } else {
                    return new RespEntity("0","来源库未查到任何数据");
                }
            }
        }
        return new RespEntity("0",flag ? "执行成功" : "执行失败");
    }

    
    public RespEntity selectTableByPlanId(Long planId) {
        Plan plan = planRepository.findById(planId).get();
        Map<String, String> map = SelectTableUtils.selectTableByPlanId(plan.getSourceDbType(), plan.getSourceDb());
        ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getSourceDbType());
        List<Map<String, Object>> datas = connectDB.query(connectDB.getConnection( plan.getSourceUsername(), plan.getSourcePwd(), plan.getSourcePort(),plan.getSourceIp(), plan.getSourceDb()), map.get("sql"));
        ArrayList<String> list = new ArrayList<>();
        for(Map<String, Object> m : datas){
            list.add((String) m.get(map.get("mapKey")));
        }
        return new RespEntity("0",list,"查询成功");
    }

    
    public RespEntity selectToTable(Long planId) {
        Plan plan = planRepository.findById(planId).get();
        Map<String, String> map = SelectTableUtils.selectTableByPlanId(plan.getTargetDbType(), plan.getTargetDb());
        ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getTargetDbType());
        List<Map<String, Object>> datas = connectDB.query(connectDB.getConnection( plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(),plan.getTargetIp(), plan.getTargetDb()), map.get("sql"));
        ArrayList<String> list = new ArrayList<>();
        for(Map<String, Object> m : datas){
            list.add((String) m.get(map.get("mapKey")));
        }
        return new RespEntity("0",list,"查询成功");
    }

    
    public RespEntity selectColumnByTable(Long planId,String tablename,String value) {
        Plan plan = planRepository.findById(planId).get();
        String dbType = "";
        String db = "";
        String username = "";
        String pwd = "";
        String port = "";
        String ip = "";
        if(value.equals("1")){
            dbType = plan.getSourceDbType();
            db = plan.getSourceDb();
            username = plan.getSourceUsername();
            pwd = plan.getSourcePwd();
            port = plan.getSourcePort();
            ip = plan.getSourceIp();
        }else{
            dbType = plan.getTargetDbType();
            db = plan.getTargetDb();
            username = plan.getTargetUsername();
            pwd = plan.getTargetPwd();
            port = plan.getTargetPort();
            ip = plan.getTargetIp();
        }

        Map<String, String> map = SelectTableUtils.selectColumnByTable(dbType,tablename,db);
        ConnectDB connectDB = StrategySimpleFactory.getInstance(dbType);
        List<Map<String, Object>> datas = connectDB.query(connectDB.getConnection( username, pwd, port,ip, db), map.get("sql"));
        ArrayList<String> list = new ArrayList<>();
        for(Map<String, Object> m : datas){
            list.add((String) m.get(map.get("mapKey")));
        }
        return new RespEntity("0",list,"查询成功");
    }

    
    @Transactional
    public RespEntity copyRule(List<Rule> rules) {

        return new RespEntity("0","复制成功");
    }

    
    public RespEntity findByPlanId(Long planId) {
        List<Rule> ruleList = ruleRepositiory.getByPlanId(planId);
        return new RespEntity("0",ruleList,"查询成功");
    }

    
    @Transactional
    public RespEntity executeRule(Map map) throws SQLException {
        String json = JSON.toJSON(map.get("rule")).toString();
        Rule rule = JSON.parseObject(json, Rule.class);
        try {
            boolean flag = true;

            //获取每个规则的sql
            String sourcesql = rule.getSourceSql();
            String targetSql= rule.getTargetSql();
            Long planId = rule.getPlanId();
            //获取方案的数据库连接信息
            Plan plan = planRepository.findById(planId).get();
            if (sourcesql == null || "".equals(sourcesql)) {
                //直接执行目标sql
                // 更新目标库
                ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getTargetDbType());
                Connection connection = connectDB.getConnection(plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(), plan.getTargetIp(), plan.getTargetDb());
                if(connection==null){
                    return new RespEntity("0","目标库库连接失败");
                }
                flag = connectDB.execute(connection,targetSql);
            } else {
                //执行来源sql查出来结果集
                ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getSourceDbType());
                Connection connection = connectDB.getConnection(plan.getSourceUsername(), plan.getSourcePwd(), plan.getSourcePort(), plan.getSourceIp(), plan.getSourceDb());
                if(connection==null){
                    return new RespEntity("0","来源库连接失败");
                }
                //
                List<RuleColumn> ruleColumns = ruleColumnRepositiory.selectRuleColumn(rule.getId());
                if(ruleColumns!=null && ruleColumns.size()>0){
                    StringBuffer buffer = new StringBuffer();
                    ruleColumns.forEach(n-> {
                        buffer.append(n.getSourceField()+",");
                    });
                    String substring = buffer.toString().substring(0, buffer.length() - 1);
                    String sql = sourcesql.toLowerCase();//转成小写
                    String sql2 = sql.split("from")[1];
                    sourcesql = "select "+substring +" from  "+sql2 +" ";
                }
                List<Map<String, Object>> datas = connectDB.query(connection, sourcesql);
                if (datas != null && datas.size() > 0) {
                    // 插入目标库
                    connectDB = StrategySimpleFactory.getInstance(plan.getTargetDbType());
                    Connection connection1 = connectDB.getConnection(plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(), plan.getTargetIp(), plan.getTargetDb());
                    if(connection1==null){
                        return new RespEntity("0","规则【"+rule.getRuleName()+"】目标库库连接失败");
                    }
                    flag = connectDB.insertBatch(connection1,datas,rule);
                } else {
                    return new RespEntity("0","规则【"+rule.getRuleName()+"】来源库未查到任何数据");
                }
            }
            return new RespEntity("0",flag ? "规则【"+rule.getRuleName()+"】执行成功" : "规则【"+rule.getRuleName()+"】执行失败");
        }catch (Exception e){
            return new RespEntity("0","规则【"+rule.getRuleName()+"】执行失败");
        }

    }

    @Transactional
    
    public RespEntity savetoRule(Map map) {
        String json = JSON.toJSON(map.get("rule")).toString();
        Rule rule = JSON.parseObject(json, Rule.class);

        Long planId = rule.getPlanId();
        //获取方案的数据库连接信息
        Plan plan = planRepository.findById(planId).get();

        rule.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        rule.setIsUse("1");
        rule.setPlanName(plan.getName());
        ruleRepositiory.save(rule);

        List<RuleColumn> dataForm = (List<RuleColumn>) map.get("dataForm");
        List<RuleColumn> lists = new ArrayList<>();
        if (dataForm!=null && dataForm.size()>0){
            for(int i = 0;i<dataForm.size();i++){
                RuleColumn ruleColumn = JSON.parseObject(JSON.toJSONString(dataForm.get(i)), RuleColumn.class);
                ruleColumn.setRuleId(rule.getId());
                ruleColumn.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                lists.add(ruleColumn);
            }
            ruleColumnRepositiory.saveAll(lists);
        }else {
            String sourceSql = rule.getSourceSql();
            if(sourceSql!=null){
                String targetSql = rule.getTargetSql();

                List<String> columns = SelectSQL.selectSQLtoDb(sourceSql,plan);

                int begin = targetSql.indexOf("(");
                int last = targetSql.indexOf(")");
                String str = targetSql.substring(begin+1,last);
                String[] split = str.split(",");

                for(int i=0;i<columns.size();i++){
                    RuleColumn ruleColumn = new RuleColumn();
                    ruleColumn.setRuleId(rule.getId());
                    ruleColumn.setSourceField(columns.get(i));
                    ruleColumn.setTargetField(split[i]);
                    lists.add(ruleColumn);
                }
                ruleColumnRepositiory.saveAll(lists);
            }
        }

        return new RespEntity("0","规则保存成功");
    }
}
