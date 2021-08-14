package com.longtu.datamove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Title: Rule
 * @description:
 * @author: hk
 * @date: 2021-04-28 15:33
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm_t_rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 方案id
     */
    private Long planId;

    /**
     * 方案名称
     */
    private String planName;

    /**
     * 来源库sql
     */
    @Lob
    private String sourceSql;

    /**
     * 目标库执行sql
     */
    @Lob
    private String targetSql;

    /**
     * 执行顺序
     */
    private int orderNum;

    private String createTime;

    /**
     * 是否启用
     */
    private String isUse;



}
