package com.longtu.datamove.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
