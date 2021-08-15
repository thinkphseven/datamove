package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanRepositiory extends JpaRepository<Plan, Long> {


}
