package edu.illinois.ugl.minrva.core.adapter;

public class ItemView {

	String value;
	int id;
	
	public ItemView(String value, int id) {
		super();
		this.value = value;
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
