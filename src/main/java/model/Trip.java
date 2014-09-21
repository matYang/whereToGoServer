package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Trip implements Serializable{

	private static final long serialVersionUID = 5653779106859496719L;
	
	private String id;
	private long creationTime;

	private ArrayList<City> trip;

	public Trip() {
		super();
	}

	public Trip(String id, long creationTime, ArrayList<City> trip) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.trip = trip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public ArrayList<City> getTrip() {
		return trip;
	}

	public void setTrip(ArrayList<City> trip) {
		this.trip = trip;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", creationTime=" + creationTime + ", trip=" + trip + "]";
	}

}
