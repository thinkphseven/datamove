package com.longtu.datamove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm_t_plan")
public class Plan {

    /**
     * 方案id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 来源库类型
     * mysql、oracle、postgersql
     */
    private String sourceDbType;

    /**
     * 来源库ip
     */
    private String sourceIp;

    /**
     * 来源库端口
     */
    private String sourcePort;

    /**
     * 来源库名
     */
    private String sourceDb;

    /**
     * 来源库用户名
     */
    private String sourceUsername;

    /**
     * 来源库密码
     */
    private String sourcePwd;

    /**
     * 目标库类型
     * mysql、oracle、postgersql
     */
    private String targetDbType;

    /**
     * 目标库ip
     */
    private String targetIp;

    /**
     * 目标库端口
     */
    private String targetPort;

    /**
     * 目标库名
     */
    private String targetDb;

    /**
     * 目标库用户名
     */
    private String targetUsername;

    /**
     * 目标库密码
     */
    private String targetPwd;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否启用
     */
    private String isUse;

    /**
     * 数据库url  不生成表字段
     */
    @Transient
    private String sourceDbAddr;

    @Transient
    private String targetDbAddr;
}
