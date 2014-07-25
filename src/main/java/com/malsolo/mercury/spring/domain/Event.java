package com.malsolo.mercury.spring.domain;

import java.util.Date;

/**
 * C:\Users\jbeneito\Documents>mongod --dbpath C:\Users\jbeneito\Documents\Projects\mongodb\mercury
 * @author jbeneito
 *
 */
public class Event {

    private Long id;
    private String data;
    private Date date;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
    
    @Override
    public String toString() {
    	return com.google.common.base.Objects.toStringHelper(this)
    			.addValue(this.id)
    			.addValue(this.data)
    			.addValue(this.date)
    			.toString();
    }

}
