package com.malsolo.mercury.spring.domain;

import java.util.Date;

/**
 * C:\Users\jbeneito\Documents>mongod --dbpath C:\Users\jbeneito\Documents\Projects\mongodb\mercury
 * @author jbeneito
 *
 */
public class Event {

    private String id;
    private Integer codeType;
    private String data;
    private Date date;
    
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
    public Integer getCodeType() {
		return codeType;
	}
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	@Override
    public String toString() {
    	return com.google.common.base.Objects.toStringHelper(this)
    			.addValue(this.id)
    			.addValue(codeType)
    			.addValue(this.data)
    			.addValue(this.date)
    			.toString();
    }

}
