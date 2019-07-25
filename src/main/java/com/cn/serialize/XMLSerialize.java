package com.cn.serialize;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class XMLSerialize implements  ISerialize {

    private final static String DEFALUT_CHARSET = "UTF-8";
    static XStream xStream = new XStream(new DomDriver(DEFALUT_CHARSET));
    static{
        XStream.setupDefaultSecurity(xStream);
    }

    @Override
    public <T> byte[] serialize(T t) {
        xStream.allowTypes(new Class[]{t.getClass()});
        try {
            return xStream.toXML(t).getBytes(DEFALUT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        xStream.allowTypes(new Class[]{tClass});
        xStream.processAnnotations(tClass);
        return (T)xStream.fromXML(new ByteArrayInputStream(data));
    }
}
