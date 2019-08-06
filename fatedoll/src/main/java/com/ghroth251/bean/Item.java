package com.ghroth251.bean;

public class Item {

	private String itemName;	
	private int itemWeight;
	private int itemprice;
	public Item() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Item(String itemName, int itemWeight, int itemprice) {
		super();
		this.itemName = itemName;
		this.itemWeight = itemWeight;
		this.itemprice = itemprice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
	}
	public int getItemprice() {
		return itemprice;
	}
	public void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", itemWeight=" + itemWeight + ", itemprice=" + itemprice + "]";
	}




}
