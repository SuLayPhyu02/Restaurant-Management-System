package restaurant_project;

public class Payment {
	private int pid;
	private String paydate;
	private int ptid;
	private int said;
	//int ptid,int said
	public Payment(int pid, String paydate, int ptid, int said) {
		super();
		this.pid = pid;
		this.paydate = paydate;
		this.ptid = ptid;
		this.said = said;
	}
	
	public Payment(int ptid, int said) {
		super();
		this.ptid = ptid;
		this.said = said;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public int getPtid() {
		return ptid;
	}
	public void setPtid(int ptid) {
		this.ptid = ptid;
	}
	public int getSaid() {
		return said;
	}
	public void setSaid(int said) {
		this.said = said;
	}
	@Override
	public String toString() {
		return pid + "," + paydate + "," + ptid + "," + said;
	}
	

}
