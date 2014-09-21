package whereToGoServer;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;

import model.Attraction;
import model.User;
import service.DaoService;

public class AttractionRedisTest {
	
	@Test
	public void testStoreFetchUser(){
		User user = new User();
		user.setId("testUser1");
		user.setFirstName("traytray");
		
		DaoService.storeUser(user);
		User result = DaoService.fetchUser(user.getId());
		
		assertTrue(result.getFirstName().equals("traytray"));
	}
	
	@Test
	public void testStoreFetchAttraction() {
		User user = new User();
		ArrayList<Attraction> favourites = new ArrayList<Attraction>();
		
		favourites.add(new Attraction("attraction:100", "disney", "dstreet100", 2, 3));
		user.setId("testUser2");
		user.setFirstName("matt");
		user.setFavourites(favourites);

		DaoService.storeUser(user);
		User resultUser = DaoService.fetchUser(user.getId());
		
		assertTrue(resultUser.getFirstName().equals("matt"));
		assertTrue(resultUser.getFavourites().get(0).getId().equals("attraction:100"));
	}
	
}
