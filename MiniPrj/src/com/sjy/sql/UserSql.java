package com.sjy.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.OracleDB;

public class UserSql {
	
	Connection conn = OracleDB.getOracleConnection();
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
//	public static void main(String[] args) {
//		numCh
//	}
	
	//회원번호 확인 체크
	public int numCheck(int num) {
		String chSelect = "SELECT MEM_NO FROM PLACE WHERE MEM_NO = ?";
		try {
			pstmt = conn.prepareStatement(chSelect);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				return 1;// 번호있음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;// 번호없음
	}

	public void ShowList() {

		
	}
	
}
