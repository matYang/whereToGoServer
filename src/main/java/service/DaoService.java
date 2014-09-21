package service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import redis.clients.jedis.Jedis;
import dao.RedisDao;
import exception.ValidationException;

public class DaoService {

	public static final Logger logger = LoggerFactory
			.getLogger(DaoService.class);
	public static final String REDIS_USER_PREFIX = "user-";
	
	public static String idGenerator() {
		String id = null;
		// TODO: generate unique 6char alpha-numerical string
		return id;
	}

	public static void set(String key, String value) {
		Jedis jedis = RedisDao.getJedis();

		try {
			jedis.set(key, value);
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}

	public static String storeUser(User user) {
		Jedis jedis = RedisDao.getJedis();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(user);

			jedis.set(REDIS_USER_PREFIX + user.getId(), jsonStr);
			return user.getId();
		} catch (JsonProcessingException e) {
			logger.warn("Store User Failed", e);
			throw new ValidationException("User with id:" + user.getId() + " Failed To Be Serialized");
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}

	public static User fetchUser(String id) {
		Jedis jedis = RedisDao.getJedis();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = jedis.get(REDIS_USER_PREFIX + id);
			return mapper.readValue(jsonStr, User.class);

		} catch (IOException e) {
			logger.warn("Fetch User Failed", e);
			throw new ValidationException("User with id: " + id + " failed to be De-Serialized");
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}

}
