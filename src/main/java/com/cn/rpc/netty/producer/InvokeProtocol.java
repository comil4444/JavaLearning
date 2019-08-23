package com.cn.rpc.netty.producer;

import lombok.Data;

@Data
public class InvokeProtocol {
    private String className;
    private String methodName;
    private Class[] parameterTypes;
    private Object[] parameterValues;
}
