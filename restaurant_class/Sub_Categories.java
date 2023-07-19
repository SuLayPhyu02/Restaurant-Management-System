package restaurant_project;

public class Sub_Categories {
	private int scid;
	private String scname;
	private int cid;
	private int cname;
	public Sub_Categories(int scid, String scname, int cid) {
		super();
		this.scid = scid;
		this.scname = scname;
		this.cid = cid;
	}
	public int getScid() {
		return scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCname() {
		return cname;
	}
	public void setCname(int cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return scid + "," + scname + "," + cid;
	}

}
