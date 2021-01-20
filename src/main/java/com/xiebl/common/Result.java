package com.xiebl.common;

import lombok.Getter;
import lombok.Setter;

/**
 * layui 返回对象
 *
 * @author
 */
@Getter
@Setter
public class Result {

    private int code;

    private String msg;

    private Object data;

    private int count;
}
