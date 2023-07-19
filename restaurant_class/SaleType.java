package restaurant_project;

public class SaleType {
	private int stid;
	private String saletname;
	
	public SaleType(int stid, String saletname) {
		super();
		this.stid = stid;
		this.saletname = saletname;
	}
	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public String getSaletname() {
		return saletname;
	}

	public void setSaletname(String saletname) {
		this.saletname = saletname;
	}

	@Override
	public String toString() {
		return stid + "," + saletname;
	}
	

}
