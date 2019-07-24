package com.cn.serialize;

import com.alibaba.fastjson.JSON;

public class JSONSerialze implements ISerialize {
    @Override
    public <T> byte[] serialize(T t) {
        return JSON.toJSONBytes(t);
    }

    @Override
    public <T> T deseralize(byte[] data, Class<T> tClass) {
        return (T)JSON.parseObject(data, tClass);
    }
}
