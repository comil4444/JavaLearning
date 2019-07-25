package com.cn.serialize;

import java.io.*;

public class JDKSerialize implements ISerialize{

    @Override
    public <T> byte[] serialize(T t) {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T)objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
