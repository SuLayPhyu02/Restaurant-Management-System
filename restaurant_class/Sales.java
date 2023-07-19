package restaurant_project;

public class Sales {
	private int said;
	private String saleDate;
	private int tableNO;
	private String remark;
	private int sid;
	private int saletypeid;
	private String saletname;
	private String ptype;
	private String sname;
	private int alltotal;
	private int income;
	public Sales(int said, String saleDate, int tableNO, String remark, int sid, int saletypeid, String saletname,
			String ptype, String sname, int alltotal) {
		super();
		this.said = said;
		this.saleDate = saleDate;
		this.tableNO = tableNO;
		this.remark = remark;
		this.sid = sid;
		this.saletypeid = saletypeid;
		this.saletname = saletname;
		this.ptype = ptype;
		this.sname = sname;
		this.alltotal = alltotal;
	}
	public Sales(int said, int tableNO, String remark, int sid, int saletypeid) {
		super();
		this.said = said;
		this.tableNO = tableNO;
		this.remark = remark;
		this.sid = sid;
		this.saletypeid = saletypeid;
	}
	public Sales(String saleDate, int income) {
		super();
		this.saleDate = saleDate;
		this.income = income;
	}
//	public Sales(int said, int tableNO) {
//		super();
//		this.said = said;
//		this.tableNO = tableNO;
//	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}

	public Sales(int alltotal) {
		super();
		this.alltotal = alltotal;
	}
	public int getSaid() {
		return said;
	}
	public void setSaid(int said) {
		this.said = said;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public int getTableNO() {
		return tableNO;
	}
	public void setTableNO(int tableNO) {
		this.tableNO = tableNO;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSaletypeid() {
		return saletypeid;
	}
	public void setSaletypeid(int saletypeid) {
		this.saletypeid = saletypeid;
	}
	public String getSaletname() {
		return saletname;
	}
	public void setSaletname(String saletname) {
		this.saletname = saletname;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getAlltotal() {
		return alltotal;
	}
	public void setAlltotal(int alltotal) {
		this.alltotal = alltotal;
	}

	@Override
	public String toString() {
		return "Sales [said=" + said + ", saleDate=" + saleDate + ", tableNO=" + tableNO + ", remark=" + remark
				+ ", sid=" + sid + ", saletypeid=" + saletypeid + ", saletname=" + saletname + ", ptype=" + ptype
				+ ", sname=" + sname + ", alltotal=" + alltotal + ", income=" + income + "]";
	}
	
}
