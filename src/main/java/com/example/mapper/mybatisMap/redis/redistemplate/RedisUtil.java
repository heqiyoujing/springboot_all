package com.example.mapper.mybatisMap.redis.redistemplate;

import org.apache.ibatis.cache.CacheKey;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtil {
	@Resource
	private StringRedisTemplate template;
	@Resource
	private RedisTemplate redisTemplate;

	public void setTemplate(StringRedisTemplate template) {
		this.template = template;
	}

	public String sget(String key) {
		return template.opsForValue().get(key);
	}


	public void setRedisTemplate(String key, Object value) {
		this.redisTemplate.opsForValue().set(key, value);
	}
	public Object getRedisTemplate(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void setList(String key, Object value) {
		this.redisTemplate.opsForList().leftPush(key, value);
	}
	public Object getList(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}


	public StringRedisTemplate getTemplate(){
		return template;
	}

	public void set(String key, String value) {
		template.opsForValue().set(key, value);
	}

	public void clean(){
		redisTemplate.discard();
	}

	/**
	 * 需要同时SessionCallback 采用了事务。没有事务采用RedisCallBack。
	 * @param list
	 * @param timeout
	 * @param unit
	 */
	public void multiSet(List<Integer> list, final long timeout, TimeUnit unit) {
		template.executePipelined(new SessionCallback<Object>() {
			@Override
			public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
				for (Integer key : list) {
					template.opsForValue().set("OPEN" + key, "1", timeout, unit);
				}
				return null;
			}
		});

	}

}
