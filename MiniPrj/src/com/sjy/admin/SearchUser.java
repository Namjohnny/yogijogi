package com.sjy.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yogijogi.obj.OracleDB;

public class SearchUser {
	
	Connection conn = OracleDB.getOracleConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	
	public void searchAll() {
		//모든 회원 보여주기
		String sql = "SELECT * FROM MEMBER";
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
				System.out.println();
				
				int no = rs.getInt("MEM_NO");
				String id = rs.getString("ID");
				String name = rs.getString("UNAME");
				String nick = rs.getString("NICK");
				String gender = rs.getString("GENDER");
				String rank = rs.getString("RANK");
				Timestamp bDate = rs.getTimestamp("BIRTH_DATE");
				
				System.out.print(no + " | ");
				System.out.print(id+ " | ");
				System.out.print(name + " | ");
				System.out.print(nick + " | ");
				System.out.print(gender + " | ");
				System.out.print(rank + " | ");
				System.out.print(bDate + " | ");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
		
	}
	
	public void search() {
		
	}
}
