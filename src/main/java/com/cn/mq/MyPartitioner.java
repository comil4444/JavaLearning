package com.cn.mq;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyPartitioner implements Partitioner {

    private Random random = new Random();

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[]
            bytes1, Cluster cluster) {
        //获取集群中指定topic的所有分区信息
        List<PartitionInfo> partitionInfos=cluster.partitionsForTopic(s);
        int numOfPartition=partitionInfos.size();
        int partitionNum=0;
        if(o==null){ //key没有设置
            partitionNum=random.nextInt(numOfPartition); //随机指定分区
        }else{
            partitionNum=Math.abs((o1.hashCode()))%numOfPartition;
        }
        System.out.println("key->"+o+",value->"+o1+"->send to partition:"+partitionNum);
        return partitionNum;
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
