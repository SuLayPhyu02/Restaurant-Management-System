package restaurant_project;

public class Dish {
	private int did;
	private String dname;
	private int dprice;
	private int subcid;
	private String scname;
	public Dish(int did, String dname, int dprice, int subcid) {
		super();
		this.did = did;
		this.dname = dname;
		this.dprice = dprice;
		this.subcid = subcid;
	}
	public Dish(int did, String dname, int dprice) {
		super();
		this.did = did;
		this.dname = dname;
		this.dprice = dprice;
	}
	
	public Dish(String dname) {
		super();
		this.dname = dname;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDprice() {
		return dprice;
	}
	public void setDprice(int dprice) {
		this.dprice = dprice;
	}
	public int getSubcid() {
		return subcid;
	}
	public void setSubcid(int subcid) {
		this.subcid = subcid;
	}
	public String getScname() {
		return scname;
	}
	public void setScname(String scname) {
		this.scname = scname;
	}
	@Override
	public String toString() {
		return did + "," + dname + "," + dprice + "," + subcid;
	}
}
