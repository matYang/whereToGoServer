package whereToGoServer;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;

import model.Attraction;
import model.Trip;
import service.DaoService;

public class AttractionRedisTest {
	
	@Test
	public void testStoreFetchUser(){
		Trip user = new Trip();
		user.setId("testUser1");
		user.setFirstName("traytray");
		
		DaoService.storeUser(user);
		Trip result = DaoService.fetchUser(user.getId());
		
		assertTrue(result.getFirstName().equals("traytray"));
	}
	
	@Test
	public void testStoreFetchAttraction() {
		Trip user = new Trip();
		ArrayList<Attraction> favourites = new ArrayList<Attraction>();
		
		favourites.add(new Attraction("attraction:100", "disney", "dstreet100", 2, 3));
		user.setId("testUser2");
		user.setFirstName("matt");
		user.setFavourites(favourites);

		DaoService.storeUser(user);
		Trip resultUser = DaoService.fetchUser(user.getId());
		
		assertTrue(resultUser.getFirstName().equals("matt"));
		assertTrue(resultUser.getFavourites().get(0).getId().equals("attraction:100"));
	}
	
}
