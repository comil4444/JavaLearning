package com.cn.serialize;

public class ProtoBufSerialize implements ISerialize {
    @Override
    public <T> byte[] serialize(T t) {
        return new byte[0];
    }

    @Override
    public <T> T deseralize(byte[] data, Class<T> tClass) {
        return null;
    }
}
