package com.pack.model;

public class UserClass {

	
	private long id;
	private String name;
	private String age;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name;
	}
	
}
