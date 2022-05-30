package com.yogijogi.hotplace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cay.reservation.Reservation;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class HotPlace {
	
	public void searchPlace() {
		
		System.out.println("===== 핫플 검색 =====");
		System.out.print("검색 >> ");
		String whatPlace = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 1;
		
		try {
			
			pstmt = conn.prepareStatement("SELECT PNO, PNAME, LOCA, PTYPE1, PTYPE2 FROM PLACE P JOIN PLACETYPE T ON P.PTNO = T.PTNO WHERE DELETE_YN = 'N' AND PNAME LIKE CONCAT(CONCAT('%', ?), '%') ORDER BY PNO");
			pstmt.setString(1, whatPlace);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
				String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
				String ptype1 = rs.getString("PTYPE1");
				String ptype2 = rs.getString("PTYPE2");
				
				System.out.println(pno + " : " + pname + " '" + loca + "' [" + ptype1 + "/" + ptype2 + "]");
			}
			System.out.println("예약하시겠습니까? (Y || N)");
			String answer = ObjController.scanStr();
			if(answer.equalsIgnoreCase("Y")) {
				new Reservation().addRsv();
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//자원 정리
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		
	}
	
//public void placeList() {
//		
//		System.out.println("===== 서울 핫플 =====");
//		
//		Connection conn = OracleDB.getOracleConnection();
//		Statement stmt = null;
//		ResultSet rs = null;
//		int cnt = 1;
//		
//		try {
//			
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("SELECT PNAME, LOCA, PTYPE1, PTYPE2 FROM PLACE P JOIN PLACETYPE T ON P.PTNO = T.PTNO WHERE DELETE_YN = 'Y'");
//			
//			while(rs.next()) {
//				String pname = rs.getString("PNAME");
//				String loca = rs.getString("LOCA");
//				String ptype1 = rs.getString("PTYPE1");
//				String ptype2 = rs.getString("PTYPE2");
//				
//				System.out.println(cnt + " : " + pname + " '" + loca + "' [" + ptype1 + "/" + ptype2 + "]");
//				cnt++;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			//자원 정리
//			OracleDB.close(conn);
//			OracleDB.close(stmt);
//			OracleDB.close(rs);
//		}
//		
//		
//	}

}
