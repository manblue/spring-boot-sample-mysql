package com.example.data.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class BaseRedisDao <T>{

	@Autowired
	RedisTemplate<Object, Object>redisTemplate;
	
	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOps;
	
//	public void save(T t) {
//		valOps.set(person.getId(), person);
//	}
//	
//	public Person getPerson(String id) {
//		return (Person) valOps.get(id);
//	}
	
}
