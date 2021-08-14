package com.longtu.datamove.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Title: User
 * @description:
 * @author: hk
 * @date: 2021-04-28 16:23
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="dm_t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String password;
}
