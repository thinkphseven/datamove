package com.longtu.datamove.service;

import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.repositiory.PlanRepositiory;
import com.longtu.datamove.repositiory.RuleRepositiory;
import com.longtu.datamove.resp.RespEntity;
import com.longtu.datamove.strategy.ConnectDB;
import com.longtu.datamove.strategy.StrategySimpleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlanService {

    @Autowired
    private PlanRepositiory planRepository;

    @Autowired
    private RuleRepositiory ruleRepositiory;
    public  RespEntity findAll() {
        Map<String,Object> m= new HashMap<>();
        List<Plan> list = planRepository.findAll();
        if (list != null && list.size() > 0) {
            list.forEach( t-> {
                t.setSourceDbAddr(t.getSourceIp() + ":" + t.getSourcePort() + "/" + t.getSourceDb());
                t.setTargetDbAddr(t.getTargetIp() + ":" + t.getTargetPort() + "/" + t.getTargetDb());
            });
        }
        m.put("data",list);
        m.put("code","0");
        return new RespEntity("0",list,"查询成功");
    }

    @Transactional
    public RespEntity execute(Long [] plans) throws SQLException {
        boolean flag = true;
        /**
         * 待优化
         */
        //每个规则逐步执行  查询每个规则的sql  根据方案查询规则
        List<Rule> list = new ArrayList<>();
        for (int k = 0; k < plans.length; k++) {

            List<Rule> var1 = ruleRepositiory.getByPlanId(plans[k]);
            //一个一个执行
           /* Plan1 rule = new Plan1();
            rule.setPlanId(plans[k]);
           *//* ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("planId", ExampleMatcher.GenericPropertyMatchers.startsWith())
                    .withMatcher("planName", ExampleMatcher.GenericPropertyMatchers.startsWith());*//*
            Example<Rule> example = Example.of(rule);*/
           // list = ruleRepositiory.findAll(example,Sort.by("orderNum"));
            list.addAll(var1);
        }
        if (list == null || list.size() == 0) {
            return new RespEntity("0", "未查询到规则信息");
        }
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
                flag =connectDB.execute(connectDB.getConnection( plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(),plan.getTargetIp(), plan.getTargetDb()),targetSql);
            } else {
                //执行来源sql查出来结果集
                ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getSourceDbType());
                List<Map<String, Object>> datas = connectDB.query(connectDB.getConnection(plan.getSourceUsername(), plan.getSourcePwd(), plan.getSourcePort(), plan.getSourceIp(), plan.getSourceDb()), sourcesql);
                if (datas != null && datas.size() > 0) {
                    // 插入目标库
                    connectDB = StrategySimpleFactory.getInstance(plan.getTargetDbType());
                    flag = connectDB.insertBatch(connectDB.getConnection(plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(), plan.getTargetIp(), plan.getTargetDb()), datas, obj,"");
                } else {
                    return new RespEntity("0", "来源库未查到任何数据");
                }
            }
        }
        return new RespEntity("0",flag ? "执行成功" : "执行失败");
    }

    public RespEntity savePlan(Plan plan) {
        plan.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        planRepository.save(plan);
        return new RespEntity("0","方案保存成功");
    }

    public Plan getPlanById(Long id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        System.out.println(optionalPlan.get());
        return optionalPlan.get();
    }

    @Transactional
    public RespEntity delPlanById(Long id) {
        planRepository.deleteById(id);
        //删除该方案下的规则
        ruleRepositiory.deleteByPlanId(id);
        return new RespEntity("0","方案删除成功");
    }


    @Transactional
    public RespEntity startOrStopPlan(List<Plan> plans, String i) {
        plans.forEach(m-> {
            m.setIsUse(i);
        });
        String mes ="";
        try {
            if ("0".equals(i)) {
                mes = "停用";
            } else {
                mes = "启用";
            }
            planRepository.saveAll(plans);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespEntity("1","方案"+mes+"失败");
        }
        return new RespEntity("0","方案"+mes+"成功");
    }

    /**
     * 复制方案
     * @param plans
     * @return
     */
    @Transactional
    public RespEntity copyPlan(List<Plan> plans) {
        for(Plan plan : plans ){
            List<Rule> ruleList = ruleRepositiory.getByPlanId(plan.getId());
            plan.setId(null);
            plan.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            planRepository.save(plan);

        }
        return new RespEntity("0","复制成功");
    }
}
