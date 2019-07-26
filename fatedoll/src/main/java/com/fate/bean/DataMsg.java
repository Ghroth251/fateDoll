package com.fate.bean;

import java.util.LinkedHashMap;

public class DataMsg {
	private int id;
	private String name;
	private String type;
	private String msg;
	private LinkedHashMap<String,Object> dData;
	public DataMsg() {
		super();
		// TODO 自动生成的构造函数存根
	}

    public DataMsg(int id, String name, String type, String msg, LinkedHashMap<String, Object> ddata) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.msg = msg;
        dData = ddata;
    }

    public LinkedHashMap<String, Object> getDdata() {
        return dData;
    }

    public void setDdata(LinkedHashMap<String, Object> ddata) {
        dData = ddata;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "DataMsg [id=" + id + ", name=" + name + ", type=" + type + ", msg=" + msg + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataMsg other = (DataMsg) obj;
		if (id != other.id)
			return false;
		return true;
	}








}
