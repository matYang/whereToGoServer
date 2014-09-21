package model;
//import java.io.Serializable;

public class Attraction {
	private String id;
	private String name;
	private String address;
	private int time;
	private int rank;
	
	public Attraction() {
		super();
	}
	
	public Attraction(String id, String name, String address, int time, int rank) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.time = time;
		this.rank = rank;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	@Override
	public String toString() {
		return "Attraction [name=" + name + ", address=" + address + ", timeOfStay=" + time
				+ ", rank=" + rank + "]";
	}
	
}
