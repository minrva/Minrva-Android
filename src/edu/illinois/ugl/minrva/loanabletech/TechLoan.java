package edu.illinois.ugl.minrva.loanabletech;

public class TechLoan {

	private String bibId;
	private String thumbnail;
	private String name;
	private String count;
	private String rank;	
	
	public TechLoan() {
		super();
		this.bibId = "";
		this.thumbnail =  "";
		this.name =  "";
		this.count =  "";
		this.rank =  "";
	}
	
	public TechLoan(String bibId, String thumbnail, String name, String count,
			String rank) {
		super();
		this.bibId = bibId;
		this.thumbnail = thumbnail;
		this.name = name;
		this.count = count;
		this.rank = rank;
	}
	
	public String getBibId() {
		return bibId;
	}
	public void setBibId(String bibId) {
		this.bibId = bibId;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
}
