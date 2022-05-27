package com.sjy.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yogijogi.obj.OracleDB;



public class User {
	private String id;
	private String pwd;
	private String name;
	private int age;
	private char gender;
	private String nick;
	private List<String> favorites;
	private String rank;
	
	//생성자
	public User() {}

	public User(String id, String pwd, String name, int age, char gender, String nick, List<String> favorites,
			String rank) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.nick = nick;
		this.favorites = favorites;
		this.rank = rank;
	}
	
	//getter setter
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public List<String> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<String> favorites) {
		this.favorites = favorites;
	}
	public String getRank() {
		return rank;
	}
	public void setRnak(String rank) {
		this.rank = rank;
	}
	
	// -----------------------------------------------------------------------------
	// method
	// Secarch
	public void connSearch() {
		System.out.println("----------------------------");
		System.out.println("검색 페이지로이동");
		// new 메서드명();
	}

	// Check
	public void connCheck() {
		System.out.println("----------------------------");
		System.out.println("체크 페이지로이동");
	}

	// Help
	public void connHelp() {
		System.out.println("----------------------------");
		System.out.println("도움말 페이지로이동");
		//new Help.showList();
	}

	// Reservation
	public void connRsv() {
		System.out.println("----------------------------");
		System.out.println("예약 페이지로이동");
	}

	// Advertisement
	public void delAd() {
		System.out.println("----------------------------");
		System.out.println("광고 페이지로이동");

	}

	// 회원탈퇴
	public void delUser() {
		System.out.println("----------------------------");
		// db연동
		String sql = "UPDATE MEMEBER SET DROP_YN='Y' WHERE ID ='"+ Login.id +"'";
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt =null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			
			if (result == 1) {
				System.out.println(Login.id +"님이 탈퇴 하셨습니다.");
			}else {System.out.println("실패");}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);OracleDB.close(pstmt);
		}
		
		// 입력 받은 id값을 참고해서
		// String sql = "delete 테이블명 where = 'id' ";
		// String sql = "update 테이블명 set drop_yn ="+ " where = ?"; //?=>id
	}
}	
