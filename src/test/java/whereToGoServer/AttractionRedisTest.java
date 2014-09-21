package whereToGoServer;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.ArrayList;

import model.Attraction;
import model.City;
import model.Trip;
import service.DaoService;
import utility.DateUtility;

public class AttractionRedisTest {
	
	@Test
	public void testStoreFetchTrip(){
		Trip trip = new Trip();
		trip.setCreationTime(DateUtility.curTime());
		trip.setTrip(new ArrayList<City>());
		
		Trip storedTrip = DaoService.storeTrip(trip);
		Trip resultTrip = DaoService.fetchTrip(storedTrip.getId());
		
		trip.setId(storedTrip.getId());
		assertTrue(trip.getId() != null);
		assertTrue(trip.toString().equals(resultTrip.toString()));
		
		DaoService.deleteTrip(trip.getId());
	}
	
	@Test
	public void testStoreFetchAttraction() {
		ArrayList<Attraction> attractions_a = new ArrayList<Attraction>();
		attractions_a.add(new Attraction("London Museum", 2, "London Ontario", "http://www.blablabla.com/photo"));
		attractions_a.add(new Attraction("University of Waterloo", 9, "Waterloo Ontario", "http://www.yolo.com/photo"));
		attractions_a.add(new Attraction("University of Toronto", 5, "Toronto Ontario", "http://bbs.york.com/photo"));
		attractions_a.add(new Attraction("Western University", 2, "London Ontario", "http://www.london.com/photo"));
		attractions_a.add(new Attraction("University of Windsor", 3, "Windsor Ontario", "http://www.windsor.com/photo"));
		
		ArrayList<Attraction> attractions_b = new ArrayList<Attraction>();
		attractions_b.add(new Attraction("The Kitchener City Hall", 0, "Kitchener Ontario uptown", "http://www.theBoringCityHallThatReallyDoesNothingSpecial.com/photo"));
		attractions_b.add(new Attraction("Victory Park", 9, "Kitchener sample drive sample unit", "http://wwwwola./photo"));
		attractions_b.add(new Attraction("Communitech Google Office", 7, "Somewhere really amazing", "http://sample.google.com/photo"));
		
		ArrayList<Attraction> attractions_c = new ArrayList<Attraction>();
		attractions_c.add(new Attraction("Boom Digital Media Group", 2, "CommuniTech Incubator", "http://www.boomdigital.ca"));
		attractions_c.add(new Attraction("Intelligent Mechantrionic Systems", 45, "King Street North", "http://ims.ca"));
		attractions_c.add(new Attraction("Genesys Lab", 25, "Middle Markham Somewhere Near First Markham Place", "http://www.genesys.com"));
		
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City(1, 3, "London", "London Ontario", "www.london.com", attractions_a));
		cities.add(new City(6, 2, "Waterloo", "Waterloo Ontario", "www.waterloo.com", attractions_b));
		cities.add(new City(9, 3, "Kitchener", "Kitchener Ontario", "www.kitchener.com", attractions_c));
		
		//Note the mock Id must be 6 character long or the store routine will consider it invalid
		Trip trip = new Trip("TESTTP", DateUtility.curTime(), cities);

		Trip storedTrip = DaoService.storeTrip(trip);
		Trip resultTrip = DaoService.fetchTrip(storedTrip.getId());
		
		assertTrue(resultTrip.getId() != null);
		assertTrue(storedTrip.toString().equals(trip.toString()));
		assertTrue(resultTrip.toString().equals(trip.toString()));
		
		DaoService.deleteTrip(trip.getId());
	}
	
}
