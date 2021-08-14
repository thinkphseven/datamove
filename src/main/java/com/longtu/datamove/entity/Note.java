package com.longtu.datamove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Title: Plan
 * @description:
 * @author: hk
 * @date: 2021-04-28 15:33
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dm_t_note")
public class Note {

    /**
     * 方案id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作人
     */
    private String userName;

    /**
     * 操作模块儿
     */
    private String module;

    /**
     * 操作方法
     */
    private String opMethod;

    /**
     * 操作时间
     */
    private String time;

    /**
     * 操作结果
     */
    private String result;


    /**
     * 是否删除
     */
    private int isDelete;


}
