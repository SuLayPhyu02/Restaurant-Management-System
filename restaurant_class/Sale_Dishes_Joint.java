package restaurant_project;

public class Sale_Dishes_Joint {
	private int sdid;
	private int orderno;
	private String dname;
	private int dprice;
	private int qty;
	private int total;
	private int saleid;
	private int dishid;
	private int count;
	public Sale_Dishes_Joint(String dname, int qty, int dprice) {
		super();
		this.dname = dname;
		this.qty = qty;
		this.dprice = dprice;
	}
	public Sale_Dishes_Joint(int sdid, int qty, int total, int saleid, int dishid) {
		super();
		this.sdid = sdid;
		this.qty = qty;
		this.total = total;
		this.saleid = saleid;
		this.dishid = dishid;
	}
	public Sale_Dishes_Joint(int sdid, int orderno, String dname, int dprice, int qty, int total, int saleid,int dishid) {
		super();
		this.sdid = sdid;
		this.orderno = orderno;
		this.dname = dname;
		this.dprice = dprice;
		this.qty = qty;
		this.total = total;
		this.saleid = saleid;
		this.dishid = dishid;
	}

	public Sale_Dishes_Joint(int sdid, int orderno, String dname, int dprice, int qty, int total) {
		super();
		this.sdid = sdid;
		this.orderno = orderno;
		this.dname = dname;
		this.dprice = dprice;
		this.qty = qty;
		this.total = total;
	}
	public Sale_Dishes_Joint(int sdid, int orderno, String dname, int dprice, int qty, int total, int saleid) {
		super();
		this.sdid = sdid;
		this.orderno = orderno;
		this.dname = dname;
		this.dprice = dprice;
		this.qty = qty;
		this.total = total;
		this.saleid = saleid;
	}
	public Sale_Dishes_Joint(int dishid,String dname, int count) {
		super();
		this.dishid = dishid;
		this.dname = dname;
		this.count = count;	
	}
	public Sale_Dishes_Joint(String dname, int dishid) {
		super();
		this.dname = dname;
		this.dishid = dishid;
	}
	
	public Sale_Dishes_Joint(String dname) {
		super();
		this.dname = dname;
	}
	public int getSdid() {
		return sdid;
	}
	public void setSdid(int sdid) {
		this.sdid = sdid;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getDprice() {
		return dprice;
	}
	public void setDprice(int dprice) {
		this.dprice = dprice;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSaleid() {
		return saleid;
	}
	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}
	public int getDishid() {
		return dishid;
	}
	public void setDishid(int dishid) {
		this.dishid = dishid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
//		@Override
//	public String toString() {
//		return "Sale_Dishes_Joint [sdid=" + sdid + ", orderno=" + orderno + ", dname=" + dname + ", dprice=" + dprice
//				+ ", qty=" + qty + ", total=" + total + ", saleid=" + saleid + ", dishid=" + dishid + "]";
//	}
	@Override
	public String toString() {
		return dishid+ "," +dname + "," + qty ;
	}
//	@Override
//	public String toString() {
//		return dname + ", dishid=" + dishid + ", count=" + count + "]";
//	}
	
	
	
}
