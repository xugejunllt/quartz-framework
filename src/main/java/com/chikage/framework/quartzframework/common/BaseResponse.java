package com.chikage.framework.quartzframework.common;

import lombok.Data;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: BaseResponse
 * @package: com.chikage.framework.quartzframework.common
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/12/16 3:56 PM
 * @version: v1.0
 */
@Data
public class BaseResponse<T> {
    int code;

    String message;

    T data;

    public BaseResponse(T data) {
        this(100, "成功", data);
    }

    public BaseResponse() {
        this("成功", null);
    }

    public BaseResponse(String message, T data) {
        this(100, message, data);
    }

    public BaseResponse(int code, String message) {
        this(code, message, null);
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
