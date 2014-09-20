package service;

import redis.clients.jedis.Jedis;
import dao.RedisDao;

public class DaoService {
	
	public static void set(String key, String value){
		Jedis jedis = RedisDao.getJedis();
		
		try{
			jedis.set(key, value);
		} finally{
			RedisDao.returnJedis(jedis);
		}
	}

}
