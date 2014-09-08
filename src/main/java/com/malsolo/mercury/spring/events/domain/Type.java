package com.malsolo.mercury.spring.events.domain;



public class Type {

    private String id;
    //@Indexed(unique = true) it fails when creating Alarms without Type due to a 11000 code, duplicate: null :|
    private Integer code;
    private String description;
    private Boolean active;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
    
    @Override
    public String toString() {
    	return com.google.common.base.Objects.toStringHelper(this)
    			.addValue(this.id)
    			.addValue(this.code)
    			.addValue(this.description)
    			.addValue(this.active)
    			.toString();
    }
    
    
}
