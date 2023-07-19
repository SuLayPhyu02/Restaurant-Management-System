package restaurant_project;

public class PaymentType {
	private int ptid;
	private String ptype;
	public PaymentType(int ptid, String ptype) {
		super();
		this.ptid = ptid;
		this.ptype = ptype;
	}
	
	public int getPtid() {
		return ptid;
	}
	public void setPtid(int ptid) {
		this.ptid = ptid;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	@Override
	public String toString() {
		return ptid + "," + ptype;
	}

}
