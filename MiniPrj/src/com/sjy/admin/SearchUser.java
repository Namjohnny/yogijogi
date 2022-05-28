package com.sjy.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.OracleDB;

public class SearchUser {
	
	Connection conn = OracleDB.getOracleConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	
	public void searchAll() {
		//모든 회원 보여주기
		String sql = "SELECT * FROM MEMEBER";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("번호" + " | ");
				System.out.print("아이디"+ " | ");
				System.out.print("이름"+ " | ");
				System.out.print("닉네임"+ " | ");
				System.out.print("성별"+ " | ");
				System.out.print("랭크"+ " | ");
				System.out.print("가입일"+ " | ");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void search() {
		
	}
}
