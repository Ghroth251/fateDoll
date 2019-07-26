package com.fate.bean;

public class Dice {

	private String name;
	private String shortName;
	private String code;
	private String State;
	
	public Dice() {
		super();
	}
	public Dice(String name, String shortName, String code, String State) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.code = code;
		this.State = State;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	




}
