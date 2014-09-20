package model;

import java.util.ArrayList;

public class Route {

	private Place departure;
	private Place destination;
	private ArrayList<Place> places;

	private boolean roundTrip;

	private long startingTime;
	private long finishingTime;
	private long creationTime;
	
	public Route(){
		
	}

	public Route(Place departure, Place destination, ArrayList<Place> places,
			boolean roundTrip, long startingTime, long finishingTime,
			long creationTime) {
		super();
		this.departure = departure;
		this.destination = destination;
		this.places = places;
		this.roundTrip = roundTrip;
		this.startingTime = startingTime;
		this.finishingTime = finishingTime;
		this.creationTime = creationTime;
	}

	public Place getDeparture() {
		return departure;
	}

	public void setDeparture(Place departure) {
		this.departure = departure;
	}

	public Place getDestination() {
		return destination;
	}

	public void setDestination(Place destination) {
		this.destination = destination;
	}

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}

	public boolean isRoundTrip() {
		return roundTrip;
	}

	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}

	public long getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(long startingTime) {
		this.startingTime = startingTime;
	}

	public long getFinishingTime() {
		return finishingTime;
	}

	public void setFinishingTime(long finishingTime) {
		this.finishingTime = finishingTime;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

}
