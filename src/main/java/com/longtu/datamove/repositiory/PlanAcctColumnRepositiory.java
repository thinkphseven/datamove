package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.PlanAcctColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanAcctColumnRepositiory extends JpaRepository<PlanAcctColumn, Long> {

    @Query(value="select * from dm_t_planacctcolumn where acct_id =?  ",nativeQuery = true)
    List<PlanAcctColumn> selectAcctColumnByAcctid(Long acctId);
    @Query(value="select * from dm_t_planacctcolumn where  type=? ",nativeQuery = true)
    List<PlanAcctColumn> selectAcctColumnByType(String type);
}
