package model;

public class Place {

	private String name;
	private long lat;
	private long lng;
	private int rank;

	public Place(){
		
	}
	
	public Place(String name, long lat, long lng, int rank) {
		super();
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public long getLng() {
		return lng;
	}

	public void setLng(long lng) {
		this.lng = lng;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Place [name=" + name + ", lat=" + lat + ", lng=" + lng
				+ ", rank=" + rank + "]";
	}

	
}
