package com.sjy.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.OracleDB;

public class numCheck {
	Connection conn = OracleDB.getOracleConnection();
	PreparedStatement pstmt, pstmt2 = null;
	ResultSet rs = null;
	
	// 번호 확인(핫플)
	public int pnumCheck(int num) {
		String chSelect = "SELECT PNO FROM PLACE WHERE PNO = ?";
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
	
	
}
