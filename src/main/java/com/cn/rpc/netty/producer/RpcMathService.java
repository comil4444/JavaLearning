package com.cn.rpc.netty.producer;

public interface RpcMathService {
    int add(int a, int b);
    int sub(int a, int b);
    int mul(int a, int b);
    int div(int a, int b);
}
