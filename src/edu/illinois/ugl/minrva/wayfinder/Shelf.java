package edu.illinois.ugl.minrva.wayfinder;


public class Shelf 
{	
	private String title;
	private String call_num;

	private String shelf_id;
	private String x;
	private String y;

	public Shelf() {}

	public Shelf(String title, String call_num, String shelf_id, String x,
			String y) {
		super();
		this.title = title;
		this.call_num = call_num;
		this.shelf_id = shelf_id;
		this.x = x;
		this.y = y;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCall_num() {
		return call_num;
	}

	public void setCall_num(String call_num) {
		this.call_num = call_num;
	}

	public String getShelf_id() {
		return shelf_id;
	}

	public void setShelf_id(String shelf_id) {
		this.shelf_id = shelf_id;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
}