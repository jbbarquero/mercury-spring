package com.malsolo.mercury.spring.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Alarm {

	@Id
	private String id;
    private String data;
    private Date date;
    //@DBRef
    private Type type;
    private List<Event> events;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
    
    @Override
    public String toString() {
    	return com.google.common.base.Objects.toStringHelper(this)
    			.addValue(this.id)
    			.addValue(this.data)
    			.addValue(this.date)
    			.addValue(this.type)
    			.addValue(this.events)    			
    			.toString();
    }

}
