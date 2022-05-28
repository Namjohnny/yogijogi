package com.sjy.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class RsvSql {
	Connection conn =  OracleDB.getOracleConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public void RsvView() {
		String sql = "SELECT * FROM RSV_LIST WHERE CANCLE = 'N' ORDER BY RSV_DATE DESC";
		
		String sql2 = "SELECT MEM_NO, NICK FROM MEMBER WHERE MEM_NO =?";
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			rs = pstmt.executeQuery();
					
			System.out.print("예약번호"+ " | ");
			System.out.print("예약자"+ " | ");
			System.out.print("예약장소"+ " | ");
			System.out.print("예약일"+ " | ");
			
			System.out.println("\n--------------------------------");
			while(rs.next()) {
				int num = rs.getInt("MEM_NO");
				pstmt2.setInt(1,num);

				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					int rsvNo = rs.getInt("RSV_NO");
					String mName = rs2.getString("NICK");
					String pName = rs.getString("PNAME");
					String rsvDate = rs.getString("RSV_DATE");
					
					System.out.print(rsvNo + " | ");
					System.out.print(mName + " | ");
					System.out.print(pName + " | ");
					System.out.print(rsvDate + " | ");
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		System.out.println();
		System.out.println("--------------------------------");
	}
		
	//장소 세부정보 확인
	public void dtailPlace() {}
	
	
	
	
	//예약 취소 
		public void delRsv() {
			//System.out.println("예약을 취소하시겠습니까? (y/n) : ");
			//String cancle = ObjController.scanStr();
			System.out.print("번호: ");
			int clnum = ObjController.scanInt();
			
			
			String sql = "UPDATE RESERVATION SET CANCLE = 'Y' WHERE RSV_NO = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, clnum);
				
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println(clnum+"번 예약 취소");
				}else {
					System.out.println("예약을 취소하지 못했습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);
			}
			
		}
}
