package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CityHistoryMap implements Serializable{
	
	private static final long serialVersionUID = 7755650067756528447L;
	public static final int HISTORYMAXAMOUNT = 10;
	
	private Map<String, City> history;
	
	public CityHistoryMap(){
		this.history = new HashMap<String, City>();
	}
	
	public Map<String, City> getHistory(){
		return this.history;
	}
	
	public void setHistory(Map<String, City> history){
		this.history = history;
	}
	
	public void setSingleHistory(String tripId, City city){
		if (this.history == null){
			this.history = new HashMap<String, City>();
		}
		//if there are 10 records, randomly remove 1
		if (this.history.size() >= HISTORYMAXAMOUNT){
			List<String> keysAsArray = new ArrayList<String>(history.keySet());
			Random r = new Random();
			this.history.remove(keysAsArray.get(r.nextInt(keysAsArray.size())));
		}

		this.history.put(tripId, city);
	}

	public void removeSingleHistory(String tripId){
		if (history != null){
			this.history.remove(tripId);
		}
	}
}
