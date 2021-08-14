package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Title: LocationRepositiory
 * @description:
 * @author: hk
 * @date: 2021-04-28 15:36
 **/
@Repository
public interface PlanRepositiory extends JpaRepository<Plan, Long> {


}
