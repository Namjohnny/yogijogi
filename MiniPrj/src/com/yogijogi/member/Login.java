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
	// page.showPage(login.loginView());

	public User loginView() {
		//로그인 화면
		
		//로그인창
		System.out.println("===== 로그인 =====");
		System.out.print("ID : ");
		String id = ObjController.scanStr();
		System.out.print("PW : ");
		String pwd = ObjController.scanStr();
		User user = checkUser(id, pwd);
		
		//가입된 유저인지 확인
		if(user != null) {
			User.LoginUserNo = user.getNo();
			System.out.println("로그인 성공!");
		}else {
			System.out.println("로그인 실패...");
		}
		
		return user;
	}
	
	public User checkUser(String scanId, String scanPwd) {
		//유저 확인
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND DROP_YN = 'N'");
			pstmt.setString(1, scanId);
			pstmt.setString(2, scanPwd);
			rs = pstmt.executeQuery();	// 결과집합을 얻어서 rs 에 저장
			// 현재 결과집합의 커서는 0번칸(==비어있는 공간)을 가리키는 상태 
			//결과집합에서, 현재 커서가 가리키는 row 중 칼럼명이 MEM_NO 인 곳의 int 타입 데이터를 읽어옴//엥 지금은 0번row인데 .... ㅠㅠ 에러 ㅠㅠ
			//이래서 필요합니다
			
			if(rs.next()) {
				int memNo = rs.getInt("MEM_NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String uname = rs.getString("UNAME");
				String nick = rs.getString("NICK");
				String rank = rs.getString("RANK");
				String dropYN = rs.getString("DROP_YN");
				User user = new User(memNo, id, pwd, uname, nick, rank, dropYN);
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//자원 정리
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return null;
		
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