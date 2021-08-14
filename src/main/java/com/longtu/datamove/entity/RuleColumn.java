package com.longtu.datamove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm_t_rulecolumn")
public class RuleColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 来源字段
     */
    private String sourceField;

    /**
     * 目标字段
     */
    private String targetField;
    /**
     * 说明
     */
    private String explain;

    private String createTime;
}
