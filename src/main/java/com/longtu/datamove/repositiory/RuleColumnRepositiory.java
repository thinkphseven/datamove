package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.RuleColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RuleColumnRepositiory extends JpaRepository<RuleColumn, Long> {
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Modifying
    @Query(value="delete from dm_t_rulecolumn where rule_id = ?",nativeQuery = true)
    void deleteByRuleId(Long id);

    @Query(value="select * from dm_t_rulecolumn where rule_id = ? order by id ",nativeQuery = true)
    List<RuleColumn> selectRuleColumn(Long ruleId);
}
