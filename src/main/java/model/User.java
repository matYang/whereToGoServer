package model;

import java.util.ArrayList;

public class User {

	private String id;
	private String firstName;
	private String lastName;
	private int gender;

	private ArrayList<Attraction> favourites;
	private ArrayList<Route> routeHistory;

	private long creationTime;

	public User() {
		super();
	}

	public User(String id, String firstName, String lastName, int gender,
			ArrayList<Attraction> favouriteAttractions, ArrayList<Route> routeHistory,
			long creationTime) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.favourites = favouriteAttractions;
		this.routeHistory = routeHistory;
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public ArrayList<Attraction> getFavourites() {
		return favourites;
	}

	public void setFavourites(ArrayList<Attraction> favourites) {
		this.favourites = favourites;
	}
	
	public ArrayList<Attraction> getFavourites(ArrayList<Attraction> favourites) {
		return this.favourites;
	}

	public ArrayList<Route> getRouteHistory() {
		return routeHistory;
	}

	public void setRouteHistory(ArrayList<Route> routeHistory) {
		this.routeHistory = routeHistory;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", favouriteAttractions="
				+ favourites + ", routeHistory=" + routeHistory
				+ ", creationTime=" + creationTime + "]";
	}

}
