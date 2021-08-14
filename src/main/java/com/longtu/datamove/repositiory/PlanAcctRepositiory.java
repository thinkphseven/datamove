package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.PlanAcct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanAcctRepositiory extends JpaRepository<PlanAcct, Long> {


}
