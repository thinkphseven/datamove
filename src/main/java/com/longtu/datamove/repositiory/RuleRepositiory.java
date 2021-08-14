package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.resp.RespEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title: LocationRepositiory
 * @description:
 * @author: hk
 * @date: 2021-04-28 15:36
 **/
@Repository
public interface RuleRepositiory extends JpaRepository<Rule, Long> {

    @Query(value="select * from dm_t_rule where plan_id = ? order by order_num ",nativeQuery = true)
    List<Rule> getByPlanId(Long plan);

    @Modifying
    @Query(value="delete from dm_t_rule where plan_id = ?",nativeQuery = true)
    void deleteByPlanId(Long id);


}
