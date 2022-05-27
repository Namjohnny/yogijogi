package com.cay.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;


public class Reservation {
	
	public void showList() {
		
	}
	
	//예약하기
	public void addRsv() {
		if(Member.loginUserNo == 0) { //유저넘버 0은 없으니까 접근 못하게 리턴함. 
			System.out.println("로그인한 유저만 예약할 수 있습니다.");
			return;
		}
		
		System.out.println("==== 예약 페이지 ====");
		System.out.print("예약 날짜 및 시간(24h) (yy-mm-dd hh:mi) : ");		
		String rsvDate = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO RESERVATION(RSVNO, NICK, PNAME, RSVDATE, CANCEL)"
				+ "VALUES(RESERVATION_NO_SEQ.NEXTVAL, ?, ?, to_date(?, 'yyyy/mm/dd hh24:mi:ss'), 'N')";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			//1, 2 > 로그인한 계정의 데이터와 핫플의 정보가 들어가게 수정...!!

			pstmt.setString(1, "원");
			pstmt.setString(2, "다온정");
			pstmt.setString(3, rsvDate);
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("예약 성공!");
			}else {
				System.out.println("예약 실패..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		

	}
	
	//예약 조회 + 검색
	public void searchRsv() {
		System.out.println("==== 예약 목록 조회 ====");
		
		Connection conn = OracleDB.getOracleConnection();
		//현재는 예약 테이블 데이터가 다 보임, 로그인한 계정의 예약만 보이게 수정해야됨!
		String sql = "SELECT * FROM RESERVATION WHERE CANCEL = 'N' ORDER BY RSVDATE DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.print("예약 번호");
			System.out.print(" | ");
			System.out.print("예약한 가게 상호");
			System.out.print(" | ");
			System.out.print("예약자 닉네임");
			System.out.print(" | ");
			System.out.print("예약일");	
			System.out.println("\n--------------------------------");

			System.out.println();
			while(rs.next()) {
				int rsvNo = rs.getInt("RSVNO");
				String pName = rs.getString("PNAME");
				String nick = rs.getString("NICK");
				String rsvDate = rs.getString("RSVDATE");
				
				System.out.print(rsvNo);
				System.out.print(" | ");
				System.out.print(pName);
				System.out.print(" | ");
				System.out.print(nick);
				System.out.print(" | ");
				System.out.print(rsvDate);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		System.out.println("예약 확인 완료.");
		System.out.println("해당 예약을 변경하거나 취소하시겠습니까?");
		System.out.println("1. 예약 변경");
		System.out.println("2. 예약 취소");
		System.out.println("3. 메인으로 돌아가기");
		int n = ObjController.scanInt();
		switch(n) {
			case 1 : modRsv(); break;
			case 2 : delRsv(); break;
			case 3 : break;
			default : System.out.println("다시 선택하세요.");
		}
		
	}
	
	//예약 변경
	public void modRsv() {
		System.out.println("===== 예약 변경 =====");
		
		Connection conn = OracleDB.getOracleConnection();

		
	}
	
	//예약 취소
	public void delRsv() {
		System.out.println("===== 예약 취소 =====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		//cancle n을 y로 바꾸기 . 
		String sql = "UPDATE RESERVATION SET PNAME = ? WHERE RSVDATE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		System.out.println("예약이 취소되었습니다.");
	}
}
