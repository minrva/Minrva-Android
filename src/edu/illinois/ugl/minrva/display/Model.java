package edu.illinois.ugl.minrva.display;

public class Model 
{
	private String title;
	private String author;
	private String thumbnail;
	private String library;
	private String[] locations;
	private String[] statuses;
	private String format;
	private String[] callnums;
	private String publisher;
	private String pubyear;
	private String summary;
		
	public Model() 
	{
		super();
		this.title = "";
		this.author = "";
		this.thumbnail = "";
		this.library = "";
		this.locations = new String[0];
		this.statuses = new String[0];
		this.format = "";
		this.callnums = new String[0];
		this.publisher = "";
		this.pubyear = "";
		this.summary = "";
	}
		
	public Model(String title, String author, String thumbnail,
			String library, String[] locations, String[] statuses, String format,
			String[] callnums, String publisher, String pubyear, String summary) 
	{
		super();
		this.title = title;
		this.author = author;
		this.thumbnail = thumbnail;
		this.library = library;
		this.locations = locations;
		this.statuses = statuses;
		this.format = format;
		this.callnums = callnums;
		this.publisher = publisher;
		this.pubyear = pubyear;
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public String[] getStatuses() {
		return statuses;
	}

	public void setStatuses(String[] statuses) {
		this.statuses = statuses;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String[] getCallnums() {
		return callnums;
	}

	public void setCallnums(String[] callnums) {
		this.callnums = callnums;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubyear() {
		return pubyear;
	}

	public void setPubyear(String pubyear) {
		this.pubyear = pubyear;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}