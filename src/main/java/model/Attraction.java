package model;

import java.io.Serializable;

public class Attraction implements Serializable{

	private static final long serialVersionUID = 115375438358374603L;
	
	private String name;
	private int duration;
	private String address;
	private String photo;

	public Attraction() {
		super();
	}

	public Attraction(String name, int duration, String address, String photo) {
		super();
		this.name = name;
		this.duration = duration;
		this.address = address;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	@Override
	public String toString() {
		return "Attraction [name=" + name + ", duration=" + duration + ", address=" + address + ", photo=" + photo + "]";
	}

}
