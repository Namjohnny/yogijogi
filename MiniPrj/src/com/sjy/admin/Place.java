package com.sjy.admin;

public class Place {
	public static Place dto = new Place();
	private int pno;
	private String pname;
	private String loca;

	private int ptno;
	private String ptype1;// 대분류
	private String ptype2;// 소분류

	public Place() {}
	
	public Place(String pname, String loca, String ptype1, String ptype2) {
		super();
		this.pname = pname;
		this.loca = loca;
		this.ptype1 = ptype1;
		this.ptype2 = ptype2;
	}
	
	//get set
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getLoca() {
		return loca;
	}
	public void setLoca(String loca) {
		this.loca = loca;
	}
	public int getPtno() {
		return ptno;
	}
	public void setPtno(int ptno) {
		this.ptno = ptno;
	}
	public String getPtype1() {
		return ptype1;
	}
	public void setPtype1(String ptype1) {
		this.ptype1 = ptype1;
	}
	public String getPtype2() {
		return ptype2;
	}
	public void setPtype2(String ptype2) {
		this.ptype2 = ptype2;
	}


	
	
	
	
}
