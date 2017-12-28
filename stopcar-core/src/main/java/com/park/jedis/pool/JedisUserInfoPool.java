package com.park.jedis.pool;

import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.JedisPool;

@Repository("jedisUserInfoPool")
public class JedisUserInfoPool extends JedisPool {
	
	@Autowired
	public JedisUserInfoPool(
			@Qualifier("jedisUserInfoConf") Config jedisUserInfoConf,
			@Value("${jedis.host}") String host,
			@Value("${jedis.port}") int port,
			@Value("${jedis.timeout}") int timeout,
			@Value("${jedis.password}") String password) {
		super(jedisUserInfoConf, host, port, timeout,password);
	}

}
