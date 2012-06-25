package edu.illinois.ugl.minrva.vufind;

public class Book 
{	
	private String bibId;
	private String title;
	private String thumbnail;
	private String author;
	private String pubYear;
	private String location;
	private String format;
	
	public Book() {
		super();
		this.bibId = "";
		this.title = "";
		this.thumbnail = "";
		this.author = "";
		this.pubYear = "";
		this.location = "";
		this.format = "";
	}
	
	public Book(String bibId, String title, String thumbnail, String author,
			String pubYear, String location, String format) {
		super();
		this.bibId = bibId;
		this.title = title;
		this.thumbnail = thumbnail;
		this.author = author;
		this.pubYear = pubYear;
		this.location = location;
		this.format = format;
	}
	
	public String getBibId() {
		return bibId;
	}
	
	public void setBibId(String bibId) {
		this.bibId = bibId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPubYear() {
		return pubYear;
	}
	
	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
}