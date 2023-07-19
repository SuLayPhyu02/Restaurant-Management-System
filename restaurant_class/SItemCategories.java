package restaurant_project;

public class SItemCategories {
	private int sicid;
	private String sicname;
	private int sicprice;
	public SItemCategories(int sicid, String sicname, int sicprice) {
		super();
		this.sicid = sicid;
		this.sicname = sicname;
		this.sicprice = sicprice;
	}
	public int getSicid() {
		return sicid;
	}
	public void setSicid(int sicid) {
		this.sicid = sicid;
	}
	public String getSicname() {
		return sicname;
	}
	public void setSicname(String sicname) {
		this.sicname = sicname;
	}
	public int getSicprice() {
		return sicprice;
	}
	public void setSicprice(int sicprice) {
		this.sicprice = sicprice;
	}
	@Override
	public String toString() {
		return sicid + "," + sicname + "," + sicprice;
	}
	

}
