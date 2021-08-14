package com.longtu.datamove.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Map;

import java.util.List;

/**
 * @Title: RespEntity
 * @description:
 * @author: hk
 * @date: 2021-05-14 16:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespEntity{

    private String code;
    private List data;
    private String msg;

    public RespEntity(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
