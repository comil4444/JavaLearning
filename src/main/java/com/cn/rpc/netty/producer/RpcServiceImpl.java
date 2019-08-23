package com.cn.rpc.netty.producer;

public class RpcServiceImpl implements RpcService {
    @Override
    public String sayHello(String name) {
        return String.format("Hi %s", name);
    }
}
