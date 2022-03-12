package com.analyticqa.amplitude;

import java.util.List;

import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;

@DefaultCoder(AvroCoder.class)
public class SampleAPIObject {
	 
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}		
	public long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}
	public String getEvent_time() {
		return event_time;
	}
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}
	public long getSession_id() {
		return session_id;
	}
	public void setSession_id(long session_id) {
		this.session_id = session_id;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}		
	public String getClient_event_time() {
		return client_event_time;
	}
	public void setClient_event_time(String client_event_time) {
		this.client_event_time = client_event_time;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDevice_brand() {
		return device_brand;
	}
	public void setDevice_brand(String device_brand) {
		this.device_brand = device_brand;
	}
	public String getDevice_manufacturer() {
		return device_manufacturer;
	}
	public void setDevice_manufacturer(String device_manufacturer) {
		this.device_manufacturer = device_manufacturer;
	}
	public String getDevice_family() {
		return device_family;
	}
	public void setDevice_family(String device_family) {
		this.device_family = device_family;
	}
	public float getLocation_lat() {
		return location_lat;
	}
	public void setLocation_lat(float location_lat) {
		this.location_lat = location_lat;
	}
	public float getLocation_lng() {
		return location_lng;
	}
	public void setLocation_lng(float location_lng) {
		this.location_lng = location_lng;
	}
	/*public List getEvent_properties() {
		return event_properties;
	}
	public void setEvent_properties(List event_properties) {
		this.event_properties = event_properties;
	}*/
	public long getAmplitude_id() {
		return amplitude_id;
	}
	public void setAmplitude_id(long amplitude_id) {
		this.amplitude_id = amplitude_id;
	}
	
	private String device_id;
	private long user_id;
	private String client_event_time;
	private long event_id;
	private String event_time;
	private long session_id;
	private String event_type;
	private String platform;
	private String device_brand;
	private String device_manufacturer;
	private String device_family;
	private float location_lat;
	private float location_lng;
	//private List event_properties;
	private long amplitude_id;
	
	
}
