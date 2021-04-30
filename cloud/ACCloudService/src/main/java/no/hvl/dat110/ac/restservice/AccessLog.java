package no.hvl.dat110.ac.restservice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		
		int id = 0;
		id = cid.incrementAndGet();
		AccessEntry accessEntry = new AccessEntry(id,message);
		log.put(id,accessEntry);
		return id;
	}
		
	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) {

		AccessEntry accessEntry = log.get(id);
		return accessEntry;
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		cid = new AtomicInteger(0);
		log.clear();
		
	}
	
	// TODO: return JSON representation of the access log
	public String toJson () {

		/*
		JsonObject innerObject = new JsonObject();
		innerObject.addProperty("id",cid.get());
		innerObject.addProperty("message",log.get(cid.get()).getMessage());
		 */

		String json = null;
		json += "[";
		for (int i = 0; i< cid.get(); i++) {
			json += "\n\t{\n";
			json += "\t\t\"id\": " +"\"" + cid.get() +"\"" + ",\n";
			json += "\t\t\"message\": " + "\"" + log.get(cid.get()).getMessage()  + "\"";
			json += "\t},";
		}
		json.substring(0,json.length()-1);
		json += "]";
		return json;
    }
}
