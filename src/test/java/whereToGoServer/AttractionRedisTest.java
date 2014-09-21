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
		ArrayList<String> attractions_a = new ArrayList<String>();
		attractions_a.add(new Attraction("London Museum", 2, "London Ontario", "http://www.blablabla.com/photo").toString());
		attractions_a.add(new Attraction("University of Waterloo", 9, "Waterloo Ontario", "http://www.yolo.com/photo").toString());
		attractions_a.add(new Attraction("University of Toronto", 5, "Toronto Ontario", "http://bbs.york.com/photo").toString());
		attractions_a.add(new Attraction("Western University", 2, "London Ontario", "http://www.london.com/photo").toString());
		attractions_a.add(new Attraction("University of Windsor", 3, "Windsor Ontario", "http://www.windsor.com/photo").toString());
		
		ArrayList<String> attractions_b = new ArrayList<String>();
		attractions_b.add(new Attraction("The Kitchener City Hall", 0, "Kitchener Ontario uptown", "http://www.theBoringCityHallThatReallyDoesNothingSpecial.com/photo").toString());
		attractions_b.add(new Attraction("Victory Park", 9, "Kitchener sample drive sample unit", "http://wwwwola./photo").toString());
		attractions_b.add(new Attraction("Communitech Google Office", 7, "Somewhere really amazing", "http://sample.google.com/photo").toString());
		
		ArrayList<String> attractions_c = new ArrayList<String>();
		attractions_c.add(new Attraction("Boom Digital Media Group", 2, "CommuniTech Incubator", "http://www.boomdigital.ca").toString());
		attractions_c.add(new Attraction("Intelligent Mechantrionic Systems", 45, "King Street North", "http://ims.ca").toString());
		attractions_c.add(new Attraction("Genesys Lab", 25, "Middle Markham Somewhere Near First Markham Place", "http://www.genesys.com").toString());
		
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City(1, 3, "London", "London Ontario", true, 254.05, 0.0, attractions_a));
		cities.add(new City(6, 2, "Waterloo", "Waterloo Ontario", false, 2514.12, 0.0, attractions_b));
		cities.add(new City(9, 3, "Kitchener", "Kitchener Ontario", true, 254.621, 0.0, attractions_c));
		
		//Note the mock Id must be 6 character long or the store routine will consider it invalid
		Trip trip = new Trip("TESTTP", DateUtility.curTime(), cities);

		Trip storedTrip = DaoService.storeTrip(trip);
		Trip resultTrip = DaoService.fetchTrip(storedTrip.getId());
		
		assertTrue(resultTrip.getId() != null);
		assertTrue(storedTrip.toString().equals(trip.toString()));
		assertTrue(resultTrip.toString().equals(trip.toString()));
		
		DaoService.deleteTrip(trip.getId());
	}
	
	@Test
	public void testCityHistory(){
		ArrayList<String> attractions_a = new ArrayList<String>();
		attractions_a.add(new Attraction("London Museum", 2, "London Ontario", "http://www.blablabla.com/photo").toString());
		ArrayList<String> attractions_b = new ArrayList<String>();
		attractions_b.add(new Attraction("The Kitchener City Hall", 0, "Kitchener Ontario uptown", "http://www.theBoringCityHallThatReallyDoesNothingSpecial.com/photo").toString());
		ArrayList<String> attractions_c = new ArrayList<String>();
		attractions_c.add(new Attraction("Boom Digital Media Group", 2, "CommuniTech Incubator", "http://www.boomdigital.ca").toString());
		
		ArrayList<City> cities_a = new ArrayList<City>();
		cities_a.add(new City(1, 3, "London", "London Ontario", true, 254.05, 0.0, attractions_a));
		cities_a.add(new City(6, 2, "Waterloo", "Waterloo Ontario", false, 2514.12, 0.0, attractions_b));
		cities_a.add(new City(9, 3, "Kitchener", "Kitchener Ontario", true, 254.621, 0.0, attractions_c));
		
		ArrayList<City> cities_b = new ArrayList<City>();
		cities_b.add(new City(1, 3, "London", "London Ontario", true, 254.05, 0.0, attractions_a));
		
		ArrayList<City> cities_c = new ArrayList<City>();
		cities_b.add(new City(1, 3, "Waterloo", "Waterloo Ontario", true, 254.05, 0.0, attractions_a));
		
		Trip trip_a = new Trip("TESTTa", DateUtility.curTime(), cities_a);
		Trip trip_b = new Trip("TESTTb", DateUtility.curTime(), cities_a);
		Trip trip_c = new Trip("TESTTc", DateUtility.curTime(), cities_b);
		Trip trip_d = new Trip("TESTTd", DateUtility.curTime(), cities_c);

		DaoService.storeTrip(trip_a);
		DaoService.storeTrip(trip_b);
		DaoService.storeTrip(trip_c);
		DaoService.storeTrip(trip_d);
		DaoService.storeTrip(trip_d);
		
		assertTrue(DaoService.getCityHistory("London", null).size() == 3);
		assertTrue(DaoService.getCityHistory("Waterloo", null).size() == 3);
		assertTrue(DaoService.getCityHistory("Kitchener", null).size() == 2);
		
		assertTrue(DaoService.getCityHistory("London", trip_a.getId()).size() == 2);
		assertTrue(DaoService.getCityHistory("Waterloo", trip_c.getId()).size() == 2);
		assertTrue(DaoService.getCityHistory("Kitchener", trip_d.getId()).size() == 2);
		
		DaoService.deleteTrip(trip_a.getId());
		DaoService.deleteTrip(trip_b.getId());
		DaoService.deleteTrip(trip_c.getId());
		DaoService.deleteTrip(trip_d.getId());
		
		assertTrue(DaoService.getCityHistory("London", null).size() == 0);
		assertTrue(DaoService.getCityHistory("Waterloo", null).size() == 0);
		assertTrue(DaoService.getCityHistory("Kitchener", null).size() == 0);
	}
	
}
