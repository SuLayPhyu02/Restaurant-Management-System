package restaurant_project;
public class Staff {
	
	private int sid;
	private String sname;
	private String sposition;
	private String semail;
	private String spassword;
	private String enrolldate;
	public Staff(int sid, String sname, String sposition, String semail, String spassword, String enrolldate) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sposition = sposition;
		this.semail = semail;
		this.spassword = spassword;
		this.enrolldate = enrolldate;
	}
	
	public Staff(String sname, String sposition, String semail) {
		super();
		this.sname = sname;
		this.sposition = sposition;
		this.semail = semail;
	}
//	public Staff(String sname) {
//		super();
//		this.sname = sname;
//	}
	public Staff(String semail) {
		super();
		this.semail = semail;
	}

	public Staff(int sid, String sname, String sposition, String semail, String spassword) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sposition = sposition;
		this.semail = semail;
		this.spassword = spassword;
	}
	public Staff(String sname, String sposition, String semail, String spassword, String enrolldate) {
		super();
		this.sname = sname;
		this.sposition = sposition;
		this.semail = semail;
		this.spassword = spassword;
		this.enrolldate = enrolldate;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSposition() {
		return sposition;
	}
	public void setSposition(String sposition) {
		this.sposition = sposition;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	public String getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(String enrolldate) {
		this.enrolldate = enrolldate;
	}
	@Override
	public String toString() {
		return semail;
	}

	
}
