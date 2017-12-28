package com.park.jedis.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.JedisPoolConfig;

@Repository("jedisUserInfoConf")
public class JedisUserInfoConf extends JedisPoolConfig {

	@Override
	public void setMaxActive(@Value("${jedis.maxActive}") int maxActive) {
		super.setMaxActive(maxActive);
	}

	@Override
	public void setMaxWait(@Value("${jedis.maxWait}") long maxWait) {
		super.setMaxWait(maxWait);
	}

	@Override
	public void setTestOnBorrow(@Value("${jedis.testOnBorrow}") boolean testOnBorrow) {
		super.setTestOnBorrow(testOnBorrow);
	}

	@Override
	public void setMinIdle(@Value("${jedis.minIdle}") int minIdle) {
		super.setMinIdle(minIdle);
	}

	@Override
	public void setMaxIdle(@Value("${jedis.maxIdle}") int maxIdle) {
		super.setMaxIdle(maxIdle);
	}
}
