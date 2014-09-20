package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDao {
	
	private static JedisPool jedisPool;
	private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);
	
	static {
		JedisPoolConfig jedisConfig = new JedisPoolConfig();
		jedisConfig.setTestOnBorrow(false);
		jedisConfig.setMinIdle(5);
		jedisConfig.setMaxWait(4000l);
		jedisPool = new JedisPool(jedisConfig, "localhost", 6379);
		
		logger.info("successfully created Redis connection pool");
	}
	
	public static Jedis getJedis() {
        return jedisPool.getResource();
    }
    
    public static void returnJedis(Jedis jedis){
    	jedisPool.returnResource(jedis);
    }

}
