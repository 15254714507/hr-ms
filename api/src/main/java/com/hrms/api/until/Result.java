package com.hrms.api.until;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前端封装的对象
 *
 * @author 孔超
 * @date 2020/4/13 23:07
 */
@Data
public class Result implements Serializable {
    /***
     * 是否成功的标志
     */
    private Integer code;
    /**
     * 返回前端的信息提示
     */
    private String msg;

    /***
     * 返回的对象
     */
    private Object object;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, Object object) {
        this.code = code;
        this.object = object;
    }

    public Result(Integer code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

}