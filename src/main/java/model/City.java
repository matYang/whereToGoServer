package model;

import java.io.Serializable;
import java.util.ArrayList;

public class City implements Serializable {

	private static final long serialVersionUID = 8493570835894373149L;

	private int day;
	private int duration;
	private String name;
	private String address;
	private String photo;
	//private ArrayList<Attraction> attractions;
	
	private ArrayList<String> attractions;
	
	public City() {

	}

	public City(int day, int duration, String name, String address, String photo, ArrayList<String> attractions) {
		super();
		this.day = day;
		this.duration = duration;
		this.name = name;
		this.address = address;
		this.photo = photo;
		this.attractions = attractions;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public ArrayList<String> getAttractions() {
		return attractions;
	}

	public void setAttractions(ArrayList<String> attractions) {
		this.attractions = attractions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "City [day=" + day + ", duration=" + duration + ", name=" + name + ", address=" + address + ", photo=" + photo + ", attractions="
				+ attractions + "]";
	}

}
