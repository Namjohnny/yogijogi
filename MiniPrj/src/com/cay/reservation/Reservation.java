package com.cay.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class Reservation {
	
	public void showList() {
		System.out.println("==== 예약 목록 조회 ====");
		
		Connection conn = OracleDB.getOracleConnection();
		//현재는 예약 테이블 데이터가 다 보임, 로그인한 계정의 예약만 보이게 수정해야됨!
		String sql = "SELECT * FROM RESERVATION WHERE CANCEL = 'N' AND MEM_NO = ? ORDER BY RSVDATE DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int memNo = rs.getInt("") ;
			pstmt.setInt(memNo, User.LoginUserNo);
			
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


	}

	//예약하기
	public void addRsv() {
		if(User.LoginUserNo == 0) { //유저넘버 0은 없으니까 접근 못하게 리턴함. 
			System.out.println("로그인한 유저만 예약할 수 있습니다.");
			return;
		}
		
		System.out.println("==== 예약 페이지 ====");
		System.out.print("예약 날짜 및 시간(24h) (yy-mm-dd hh:mi) : ");		
		String rsvDate = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO RESERVATION(RSV_NO, PAY_NO, MEM_NO, PNO, RSV_DATE, CANCEL)"
				+ "VALUES(RESERVATION_NO_SEQ.NEXTVAL, ?, ?, ?, to_date(?, 'yyyy/mm/dd hh24:mi'), 'N')";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//로그인 연결... 가져오기...? executQurery.. 
			rs = pstmt.executeQuery();
			//1, 2 > 로그인한 계정의 데이터와 핫플의 정보가 들어가게 수정...!!
			int payNo = rs.getInt("PAY_NO");
			int memNo = rs.getInt("MEM_NO");
			int pNo = rs.getInt("PNO");

			pstmt.setInt(1, payNo);
			pstmt.setInt(2, memNo);
			pstmt.setInt(3, pNo);
			pstmt.setString(4, rsvDate);
			
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
	
	
	//예약 검색
	public void checkRsv() {
		
		Connection conn = OracleDB.getOracleConnection();
		

		
		System.out.println("조회할 예약 번호 입력 : ");
		int rsvNo = ObjController.scanInt();
		
		//이걸 JOIN문으로 수정해서 SELECT하나..? 
		String sql = "SELECT * FROM RESERVATION R, MEMBER M WHERE R.MEM_NO = M.NO AND RSV_NO = ? AND DELETE_YN = 'N'";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rsvNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String pName = rs.getString("PNAME");
				String payNo = rs.getString("PAY_NO");
				int rsvDate = rs.getInt("RSV_DATE");
				
				System.out.println("핫플 이름 : " + pName);
				System.out.println("예약 일정 : " + rsvDate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
		}

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

		System.out.println("변경 원하는 날짜와 시간을 정해주세요.");
		
		String sql = "UPDATE RESERVATION SET PNAME = ? WHERE RSVDATE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		

		
	}
	
	//예약 취소
	public void delRsv() {
		System.out.println("===== 예약 취소 =====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "UPDATE RESERVATION SET PNAME = ? WHERE RSVDATE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
	}
}
