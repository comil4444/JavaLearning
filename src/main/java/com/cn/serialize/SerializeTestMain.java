package com.cn.serialize;

import com.cn.serialize.model.Person;
import com.google.protobuf.InvalidProtocolBufferException;

public class SerializeTestMain {

    private static ISerialize jsonSerialize = new JSONSerialize();
    private static ISerialize jdkSerialize = new JDKSerialize();
    private static ISerialize xmlSerialize = new XMLSerialize();

    public static void main(String[] args) throws InvalidProtocolBufferException {
        Person person = new Person(20,"Eric");

        test(person, jsonSerialize);
        test(person, xmlSerialize);
        test(person, jdkSerialize);
        testPB();
    }

    private static void testPB() throws InvalidProtocolBufferException {
        PersonOuterClass.Person.Builder builder = PersonOuterClass.Person.newBuilder();
        builder.setAge(20);
        builder.setName("Eric");
        PersonOuterClass.Person p = builder.build();
        long start = System.currentTimeMillis();
        byte[] b = p.toByteArray();
        System.out.print("ProtoBufSerialize" + "\t format size:" + b.length + "\t");
        p = PersonOuterClass.Person.parseFrom(b);
        System.out.print((System.currentTimeMillis() - start + "\t"));
        System.out.println(p.getClass().getSimpleName() + " name:" + p.getName() + "age:" + p.getAge() );
    }

    private static void test(Object person, ISerialize iSerialize) {
        long start = System.currentTimeMillis();
        byte[] data = iSerialize.serialize(person);
        System.out.print(iSerialize.getClass().getSimpleName() + "\t format size:" + data.length + "\t");
        person = iSerialize.deserialize(data, Person.class);
        System.out.print((System.currentTimeMillis() - start + "\t"));
        System.out.println(person);
    }

}
