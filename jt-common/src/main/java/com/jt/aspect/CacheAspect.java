package com.jt.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.jt.annotation.RequiredCache;
import com.jt.util.ObjectMapperUtil;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Slf4j
@Component
@Aspect
public class CacheAspect {
	//分片机制
//	@Autowired
//	private ShardedJedis jedis;
	//哨兵机制
//	@Autowired
//	private Jedis jedis;
	@Autowired
	private JedisCluster jedis;
	
	@Around("@annotation(requiredCache)")
	public Object around(ProceedingJoinPoint pjp,RequiredCache requiredCache) throws Throwable {
		String key = getKey(pjp,requiredCache);
		String result = jedis.get(key);
		Object data = null;
		try {
			if(StringUtils.isEmpty(result)) {
				//缓存没数据,目标方法执行查询数据
				data = pjp.proceed();
				String value = ObjectMapperUtil.toJSON(data);
				
				if(requiredCache.seconds()==0) {
					jedis.set(key, value);
				}else {
					jedis.setex(key,requiredCache.seconds(), value);
				}
				log.info("aop查询缓存");
			}else {
				//表示缓存中有数据
				Class<?> returnClass = getClass(pjp);
				data = ObjectMapperUtil.toObject(result,returnClass);
				log.info("aop访问缓存");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return data;
	}
	private String getKey(ProceedingJoinPoint pjp, RequiredCache requiredCache) {
		String key = requiredCache.key();
		if(!StringUtils.isEmpty(key)) {
			return key;
		}
		String clsName = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		key = clsName+"."+methodName+"::"+args[0];
		return key;
	}
	private Class<?> getClass(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		return signature.getReturnType();
	}
}
