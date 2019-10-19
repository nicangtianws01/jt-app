package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@PropertySource("classpath:/properties/redis.properties")
@Configuration
public class RedisConfig {
//	@Value("${redis.host}")
//	private String host;
//	@Value("${redis.port}")
//	private Integer port;
	@Value("${redis.nodes}")
	private String nodes;
	
	/* <bean id="jedis" class="com.jt.config.Jedis" /> */
//	@Bean//简单jedis整合
//	public Jedis jedis() {
//		return new Jedis(host, port);
//	}
	
//	@Bean //分片机制
//	public ShardedJedis shardedJedis() {
//		List<JedisShardInfo> list = getJedisInfoList();
//		return new ShardedJedis(list);
//	}
//	public List<JedisShardInfo> getJedisInfoList(){
//		String[] array = nodes.split(",");
//		List<JedisShardInfo> list = new ArrayList<>();
//		for (String string : array) {
//			String host = string.split(":")[0];
//			Integer port = Integer.valueOf(string.split(":")[1]);
//			list.add(new JedisShardInfo(host,port));
//		}
//		return list;
//	}
//	//哨兵机制
//	@Bean
//	@Scope("prototype") //多例对象
//	public Jedis jedis(JedisSentinelPool sentinelPool){
//
//		return sentinelPool.getResource();
//	}
//	@Bean //单例
//	public JedisSentinelPool jedisSentinelPool() {
//		Set<String> sentinels = new HashSet<>();
//		sentinels.add(nodes);
//		JedisSentinelPool sentinelPool = 
//				new JedisSentinelPool("mymaster", sentinels);
//		return sentinelPool;
//	}
	
	@Bean //集群
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodeSet = new HashSet<HostAndPort>();
		String[] arrayNodes = nodes.split(",");
		for (String node : arrayNodes) { //node=host:port
			String host = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			nodeSet.add(new HostAndPort(host, port));
		}
		return new JedisCluster(nodeSet);
	}
	
}
