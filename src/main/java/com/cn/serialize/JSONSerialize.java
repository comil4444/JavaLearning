package com.cn.serialize;

import com.alibaba.fastjson.JSON;

public class JSONSerialize implements ISerialize {
    @Override
    public <T> byte[] serialize(T t) {
        return JSON.toJSONBytes(t);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        return (T)JSON.parseObject(data, tClass);
    }
}
