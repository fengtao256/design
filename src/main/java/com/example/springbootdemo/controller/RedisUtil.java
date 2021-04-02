package com.example.springbootdemo.controller;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

  private static JedisCluster jedisCluster;
  //静态初始化
  static{
    //集群信息

    Set<HostAndPort> nodes = new HashSet<HostAndPort>();
    nodes.add(new HostAndPort("172.18.195.27",7001)) ;
    nodes.add(new HostAndPort("172.18.195.27",7002)) ;
    nodes.add(new HostAndPort("172.18.195.27",7003)) ;
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    // 最大连接数
    poolConfig.setMaxTotal(50);
    // 最大空闲数
    poolConfig.setMaxIdle(3);
    //得到的是redis的集群模式
    jedisCluster = new JedisCluster(nodes,poolConfig);
  }
  //防止实例化
  private RedisUtil(){}
  //获取一个redis连接
  public static JedisCluster getJedis() {
    return jedisCluster;
  }

  public static void main(String[] args){
    JedisCluster jedis = RedisUtil.getJedis() ;
    System.out.println("add :"+jedis.set("name", "fengtao"));
    System.out.println("get :"+jedis.get("name"));
    System.out.println("del :"+jedis.del("name"));
  }

}
