package restaurant_project;

public class ShoppingItems {
	private int siid;
	private int iqty;
	private String qtyunit;
	private String buydate;
	private int total;
	private int staffid;
	private int sicateid;
	private String sname;
	private String sicname;
	private int sicprice;
	private int income;
	private int expense;
	private int profit;
	public ShoppingItems(int siid, int iqty, String qtyunit, String buydate, int total, int staffid, int sicateid,
			String sname, String sicname, int sicprice) {
		super();
		this.siid = siid;
		this.iqty = iqty;
		this.qtyunit = qtyunit;
		this.buydate = buydate;
		this.total = total;
		this.staffid = staffid;
		this.sicateid = sicateid;
		this.sname = sname;
		this.sicname = sicname;
		this.sicprice = sicprice;
	}
	public ShoppingItems(int siid, int iqty, String qtyunit, int total, int staffid, int sicateid) {
		super();
		this.siid = siid;
		this.iqty = iqty;
		this.qtyunit = qtyunit;
		this.total = total;
		this.staffid = staffid;
		this.sicateid = sicateid;
	}
	public ShoppingItems(String buydate, int expense) {
		super();
		this.buydate = buydate;
		this.expense = expense;
	}
	public ShoppingItems(String buydate, int income, int expense, int profit) {
		super();
		this.buydate = buydate;
		this.income = income;
		this.expense = expense;
		this.profit = profit;
	}
	public ShoppingItems(String qtyunit) {
		super();
		this.qtyunit = qtyunit;
	}
	public int getSiid() {
		return siid;
	}
	public void setSiid(int siid) {
		this.siid = siid;
	}
	public int getIqty() {
		return iqty;
	}
	public void setIqty(int iqty) {
		this.iqty = iqty;
	}
	public String getQtyunit() {
		return qtyunit;
	}
	public void setQtyunit(String qtyunit) {
		this.qtyunit = qtyunit;
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}
	public int getSicateid() {
		return sicateid;
	}
	public void setSicateid(int sicateid) {
		this.sicateid = sicateid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
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
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	@Override
	public String toString() {
		return "ShoppingItems [siid=" + siid + ", iqty=" + iqty + ", qtyunit=" + qtyunit + ", buydate=" + buydate
				+ ", total=" + total + ", staffid=" + staffid + ", sicateid=" + sicateid + ", sname=" + sname
				+ ", sicname=" + sicname + ", sicprice=" + sicprice + ", income=" + income + ", expense=" + expense
				+ ", profit=" + profit + "]";
	}

}
