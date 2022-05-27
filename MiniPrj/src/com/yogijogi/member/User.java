package com.yogijogi.member;

public class User {

	//생성자
	public User(int memNo, String id, String pwd, String uname, String nick, String rank, String dropYN) {
		super();
		this.memNo = memNo;
		this.id = id;
		this.pwd = pwd;
		this.uname = uname;
		this.nick = nick;
		this.rank = rank;
		this.dropYN = dropYN;
	}
	
	public static int LoginUserNo;
	
	//필드
	private int memNo;
	private String id;
	private String pwd;
	private String uname;
	private String nick;
	private String rank;
	private String dropYN;
	public int getMemNo() {
		return memNo;
	}
	
	
	//getter/setter
	public int getNo() {
		return memNo;
	}
	public void setNo(int memNo) {
		this.memNo = memNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDropYN() {
		return dropYN;
	}
	public void setDropYN(String dropYN) {
		this.dropYN = dropYN;
	}


	//toString
	@Override
	public String toString() {
		return nick + "님 [아이디:" + id + ", 이름:" + uname + ", 등급:" + rank + "]";
	}
	
	
}
