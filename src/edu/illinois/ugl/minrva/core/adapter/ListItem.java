package edu.illinois.ugl.minrva.core.adapter;

import java.util.ArrayList;


public class ListItem extends ArrayList<ItemView> {

	private static final long serialVersionUID = 1L;

	Object data;
	int id;
	boolean enabled;
	
	public ListItem(Object data, int id, boolean enabled) {
		super();
		this.data = data;
		this.id = id;
		this.enabled = enabled;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
