package com.longtu.datamove.service;

import com.alibaba.fastjson.JSON;
import com.longtu.datamove.entity.*;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 前端循环调用执行规则
     * @param map
     * @return
     * @throws SQLException
     */
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
                    sourcesql = sourcesql.replace("#{id}", "id,name,psw ");
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

    /**
     * 保存规则和字段
     * @param map
     * @return
     */
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

    /**
     * 科目辅助核算，辅助值级(程序代码处理)
     * @param map
     * @return
     */
    @Transactional
    public RespEntity subjectToAccount(Map map) {
        String json = JSON.toJSON(map.get("rule")).toString();
        Rule rule = JSON.parseObject(json, Rule.class);

        Long planId = rule.getPlanId();
        //获取方案的数据库连接信息
        Plan plan = planRepository.findById(planId).get();

        String acctForm = JSON.toJSON(map.get("acctForm")).toString();
        PlanAcct planAcct = JSON.parseObject(acctForm, PlanAcct.class);
        String agencyCode = planAcct.getAgencyCode();
        String mofDivCode = planAcct.getMofDivCode();
        try {
            //1.先查询u8库的fzhs字段
            String sql1 = "select zth+'-'+kjnd+'-'+kmdm, fzhs, kjnd from gl_kmxx where fzhs <> ''";
            ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getSourceDbType());
            Connection connection = connectDB.getConnection(plan.getSourceUsername(), plan.getSourcePwd(), plan.getSourcePort(), plan.getSourceIp(), plan.getSourceDb());
            if(connection==null){
                return new RespEntity("0","来源库连接失败");
            }
            List<Map<String, Object>> mapList = connectDB.query(connection, sql1);
            List<List<String>> list = new ArrayList<>();
            //获取拆分后的值，拆分后fzdm+拆分逗号值，如fzhs=,1,2,拆分后，fzdm1,fzdm2
            for(Map<String, Object> fzhsMap: mapList){
                String fzhs = String.valueOf(fzhsMap.get("fzhs"));
                fzhs = fzhs.substring(1, fzhs.length()-1);
                String[] fzhsArr = fzhs.split(",");
                for (int i=0;i<fzhsArr.length;i++){
                    List<String> setList = new ArrayList<>();
                    setList.add(UUID.randomUUID().toString().replaceAll("-", ""));
                    setList.add("UUID.randomUUID().toString().replaceAll(\"-\", \"\")");//科目取上条语句查出科目
                    setList.add(agencyCode);//单位编码
                    setList.add("fzdm"+fzhsArr[i]);
                    setList.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    setList.add("g1");
                    setList.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    setList.add("g1");
                    setList.add("2");//是否删除
                    setList.add(mofDivCode);//取录入区划值
                    setList.add("9daca3e37a3949a3b37bd1aff784fe3e");//账套主键
                    setList.add("2019");//会计年度取科目对应
                    setList.add("1");
                    setList.add("1");
                    setList.add("0");
                }
            }

            //2.科目辅助核算表保存拆分后的数据
            String sql2 = "insert into gla_account_element_set (data_id, account_cls, agency, element_code, create_time, create_user, update_time, update_user, is_delete, mof_div_code, acct_set, year, is_enabled, is_required, is_disabled)";
            int begin = sql2.indexOf("(");
            int last = sql2.indexOf(")");
            String str = sql2.substring(begin+1,last);
            String[] columns = str.split(",");

            ConnectDB connectDB2 = StrategySimpleFactory.getInstance(plan.getTargetDbType());
            Connection connection2 = connectDB2.getConnection(plan.getTargetUsername(), plan.getTargetPwd(), plan.getTargetPort(), plan.getTargetIp(), plan.getTargetDb());
            if(connection2==null){
                return new RespEntity("0","规则【"+rule.getRuleName()+"】目标库库连接失败");
            }
            PreparedStatement prest = connection2.prepareStatement(sql2, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(List s : list){
                for (int i=0; i < columns.length; i++) {
                    prest.setObject(i, s.get(i));
                }
                prest.addBatch();
            }
            prest.executeBatch();

            //3.根据辅助核算对应表更新element_code
            String sql3 = " update gla_account_element_set set element_code = ? where element_code = ? ";
            prest = connection2.prepareStatement(sql3, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(Map<String, Object> map1 : mapList){
                String sourceName = String.valueOf(map1.get("sourceName"));
                if(!"null".equals(sourceName) && !"".equals(sourceName)){
                    prest.setObject(1, String.valueOf(map1.get("targetName")));
                    prest.setObject(2, sourceName);
                }
                prest.addBatch();
            }
            prest.executeBatch();

            //4.根据辅助核算对应表，将对应值级查询来源库数据插入到目标库
            String lbdm = "";
            String sql4 = "select * from GL_Fzxzl  where lbdm = '"+lbdm+"' ";
            List<PlanAcctColumn> acctColumnData = (List<PlanAcctColumn>) map.get("acctColumnData");
            for (PlanAcctColumn pac : acctColumnData){
                if(pac.getSourceName()!=null && pac.getSourceName()!="" && "1".equals(pac.getIsSelf())){
                    lbdm = pac.getSourceName().replace("fzdm", "");
                    List<Map<String, Object>> lbdmList = connectDB.query(connection, sql1);
                    String sql5 = "INSERT INTO "+pac.getTablecode()+" (data_id, code, name, short_name, whole_name, parent_id, level_no, is_leaf, is_enabled, is_standard, status, type, start_date, end_date, acct_set, agency, data1, data2, data3, data4, data5, data6, data7, data8, data9, mof_div_code, year, is_deleted, update_time, create_time, create_user, update_user)";

                }
            }

            if(connection !=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection2 !=null){
                try{
                    connection2.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){

        }

        return new RespEntity("0","执行成功");
    }


}
