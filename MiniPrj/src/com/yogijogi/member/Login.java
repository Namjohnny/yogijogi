package com.yogijogi.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class Login {

	public void loginView() {
		//로그인 화면
		
		//로그인창
		System.out.println("===== 로그인 =====");
		System.out.print("ID : ");
		String id = ObjController.scanStr();
		System.out.print("PW : ");
		String pwd = ObjController.scanStr();
		
		//가입된 유저인지 확인
		if(checkUser(id, pwd)) {
			System.out.println("로그인 성공!");
		}else {
			System.out.println("로그인 실패...");
		}
	}
	
	public boolean checkUser(String scanId, String scanPwd) {
		//유저 확인
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND DROP_YN = 'N'");
			pstmt.setString(1, scanId);
			pstmt.setString(2, scanPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					return true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 관련 에러 !!!");
		} finally {
			//자원 정리
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return false;
		
	}
	
	
//	public void rankUp(String userId) {
//		//등급 업 -> 광고 삭제
//		//광고 삭제는 DB의 RANK가 특정 숫자 이상이면 삭제 가능
//		
//		Connection conn = OracleDB.getOracleConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = conn.prepareStatement("UPDATE MEMBER SET RANK = RANK+1 WHERE ID = ?");
//			pstmt.setString(1, userId);
//			rs = pstmt.executeQuery();
//					
//			
//		} catch (SQLException e) {
//			System.out.println("SQL 관련 에러 !!!");
//		} finally {
//			//자원 정리
//			OracleDB.close(conn);
//			OracleDB.close(pstmt);
//			OracleDB.close(rs);
//		}
//		
//	}

}
