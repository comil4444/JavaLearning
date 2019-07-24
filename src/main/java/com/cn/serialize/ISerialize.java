package com.cn.serialize;

public interface ISerialize {
    <T> byte[] serialize(T t);
    <T> T deseralize(byte[] data, Class<T> tClass);
}
