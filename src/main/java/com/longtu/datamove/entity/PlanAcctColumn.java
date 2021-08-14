package com.longtu.datamove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm_t_planacctcolumn")
public class PlanAcctColumn {

    @Id
    private Long id;

    private Long acctId;
    /**
     * 来源字段
     */
    private String sourceName;

    /**
     * 目标字段
     */
    private String targetName;
    /**
     * 说明
     */
    private String explain;

    private String type;



}
