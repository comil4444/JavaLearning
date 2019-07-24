package com.cn.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JDKSerialize implements ISerialize{

    @Override
    public <T> byte[] serialize(T t) {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
            objectOutputStream.writeObject(t);
        }catch (Exception e){

        }


        return new byte[0];
    }

    @Override
    public <T> T deseralize(byte[] data, Class<T> tClass) {
        return null;
    }
}
