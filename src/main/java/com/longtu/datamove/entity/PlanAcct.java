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
@Table(name = "dm_t_planacct")
public class PlanAcct {
    @Id
    private Long id;
    private String acctName;
    private String planId;
    private String planName;
    private String mofDivCode;
    private String agencyCode;

}
