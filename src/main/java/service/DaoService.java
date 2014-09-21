package service;

import java.io.IOException;

import model.City;
import model.Trip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import utility.DateUtility;
import utility.RandomUtility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.RedisDao;
import exception.NotFoundException;
import exception.ValidationException;

public class DaoService {

	public static final Logger logger = LoggerFactory.getLogger(DaoService.class);
	public static final String REDIS_TRIP_PREFIX = "trip-";
	public static final int IDLENGTH = 6;
	public static final long IDMAXAMOUNT = 36 * 36 * 36 * 36 * 36 * 36;

	public static void set(String key, String value) {
		Jedis jedis = RedisDao.getJedis();
		try {
			jedis.set(key, value);
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}

	public static Trip storeTrip(Trip trip) {
		Jedis jedis = RedisDao.getJedis();

		try {
			if (trip == null){
				throw new ValidationException("Trip must be specified");
			}

			if (trip.getId() == null || trip.getId().length() != IDLENGTH) {
				long loopCounter = 0;
				IdGenLoop: while (true) {
					String newId = RandomUtility.ramId();
					if (jedis.get(REDIS_TRIP_PREFIX + newId) == null){
						trip.setId(newId);
						//set the creation time of the current trip, in UTC timezone
						trip.setCreationTime(DateUtility.curTime());
						break IdGenLoop;
					}
					loopCounter++;
					if (loopCounter == IDMAXAMOUNT){
						throw new ValidationException("Max Amount of Trips Reached. Can Not Create New Trips");
					}
				} //End IdGenLoop
			}
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString(trip);
			jedis.set(REDIS_TRIP_PREFIX + trip.getId(), jsonStr);
			
			return trip;
		} catch (JsonProcessingException e) {
			logger.warn("Store Trip Failed", e);
			throw new ValidationException("Trip with id:" + trip.getId() + " Failed To Be Serialized");
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}

	public static Trip fetchTrip(String id) {
		Jedis jedis = RedisDao.getJedis();

		try {
			if (id == null){
				throw new ValidationException("Id Must Be Specified When Fetching A Trip");
			}
			String jsonStr = jedis.get(REDIS_TRIP_PREFIX + id);
			if (jsonStr == null){
				throw new NotFoundException("Trip Not Found. Please Create A New One");
			}
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonStr, Trip.class);

		} catch (IOException e) {
			logger.warn("Fetch Trip Failed", e);
			throw new ValidationException("Trip with id: " + id + " failed to be De-Serialized");
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}

	public static void deleteTrip(String id){
		Jedis jedis = RedisDao.getJedis();

		try {
			if (id == null){
				throw new ValidationException("Id Must Be Specified When Deleting A Trip");
			}
			jedis.del(REDIS_TRIP_PREFIX + id);
		} finally {
			RedisDao.returnJedis(jedis);
		}
	}
	
	private static void addCityHistory(City city){
		
	}
	
}
