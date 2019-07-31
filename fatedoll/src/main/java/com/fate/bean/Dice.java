package com.fate.bean;

import javax.persistence.*;

@Entity
@Table(name = "dice") //建立实体类和表的映射关系
public class Dice {

	@Id//声明当前私有属性为主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
	private Long id;
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
	public Dice(Long id,String name, String shortName, String code, String State) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.code = code;
		this.State = State;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
