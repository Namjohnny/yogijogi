package com.cay.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.phs.payMethod.ChoicePayMethod;
import com.phs.payment.Pay;
import com.yogijogi.main.Main;
import com.yogijogi.member.MyPage;
import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class Reservation {
	
	public static void showList() {
		System.out.println("==== 예약 목록 조회 ====");
		
		Connection conn = OracleDB.getOracleConnection();
		//현재는 예약 테이블 데이터가 다 보임, 로그인한 계정의 예약만 보이게 수정해야됨!
		String sql = "SELECT * FROM RSV_LIST WHERE CANCEL = 'N' AND MEM_NO = ? ORDER BY RSV_NO";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, User.LoginUserNo);
			rs = pstmt.executeQuery();
			
			System.out.print("예약 번호");
			System.out.print(" | ");
			System.out.print("예약한 가게 상호");
			System.out.print(" | ");
			System.out.print("예약일");	
			System.out.println("\n--------------------------------");

			System.out.println();
			while(rs.next()) {
				int rsvNo = rs.getInt("RSV_NO");
				String pName = rs.getString("PNAME");
				Date rsvDate = rs.getDate("RSV_DATE");
				
				System.out.print(rsvNo);
				System.out.print(" | ");
				System.out.print(pName);
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
		
		checkRsv();

	}

	//예약하기
	public static void addRsv() {
		if(User.LoginUserNo == 0) { //유저넘버 0은 없으니까 접근 못하게 리턴함. 
			System.out.println("로그인한 유저만 예약할 수 있습니다.");
			return;
		}
		
		System.out.println("==== 예약 페이지 ====");
		System.out.print("예약 날짜 (yy-mm-dd) : ");		
		String rsvDate = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO RESERVATION(RSV_NO, MEM_NO, P_NO, RSV_DATE, CANCEL)"
				+ "VALUES(RESERVATION_NO_SEQ.NEXTVAL, ?, ?, to_date(?, 'yyyy/mm/dd'), 'N')";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//로그인 연결... 가져오기...? executQurery.. 
			rs = pstmt.executeQuery();
			//1, 2 > 로그인한 계정의 데이터와 핫플의 정보가 들어가게 수정...!!
			int memNo = rs.getInt("MEM_NO");
			int pNo = rs.getInt("PNO");

			pstmt.setInt(1, memNo);
			pstmt.setInt(2, pNo);
			pstmt.setString(3, rsvDate);
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("예약금 20,000원을 결제합니다.");
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				Pay.payNow02();
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
	
	
	//예약 확인
	public static void checkRsv() {
		
		Connection conn = OracleDB.getOracleConnection();
	
		System.out.println("조회할 예약 번호 입력 : ");
		int no = ObjController.scanInt();
		
//		String sql = "SELECT * FROM RESERVATION R, MEMBER M WHERE R.MEM_NO = M.NO AND RSV_NO = ? AND DELETE_YN = 'N'";
		String sql = "SELECT * FROM RSV_VIEW WHERE RSV_NO = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int rsvNo = rs.getInt("RSV_NO");
				Date rsvDate = rs.getDate("RSV_DATE");
				String pName = rs.getString("P_NAME");
				String loca = rs.getString("LOCA");
				int payNo = rs.getInt("PAY_NO");
				String pthName = rs.getString("PTH_NAME");
				
				System.out.println("예약 번호 : " + rsvNo);
				System.out.println("예약일 : " + rsvDate);
				System.out.println("상호명 : " + pName);
				System.out.println("가게 주소 : " + loca);
				System.out.println("결제 번호 : " + payNo);
				System.out.println("결제 수단 : " + pthName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
		}

		System.out.println("해당 예약을 변경하거나 취소하시겠습니까?");
		System.out.println("1. 예약 변경");
		System.out.println("2. 예약 취소");
		System.out.println("3. 돌아가기");
		
		int n = ObjController.scanInt();
		
		switch(n) {
			case 1 : modRsv(); break;
			case 2 : delRsv(); break;
			case 3 : //마이페이지로 돌아가려고 하는데 유저 객체 만들어서 이렇게 쓰면 되는 게 맞는지...??? 
				//MyPage.showPage(user); break;
			default : System.out.println("다시 선택하세요.");
		}
		
	}
	
	//예약 변경
	public static void modRsv() {
		System.out.println("===== 예약 변경 =====");
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;		
		ResultSet rs2 = null;
	
		//변경 날짜 입력 받기
		System.out.print("변경 원하는 날짜와 시간 : ");
		String rsvDate = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		//데이터 수정
		String sql2 = "UPDATE RESERVATION SET RSV_DATE = TO_DATE(?) WHERE RSV_NO = ?";
		
		try {
			String sql1 = "SELECT * FROM RSV_VIEW";
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int rsvNo = rs.getInt("RSV_NO");
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, rsvDate);
			pstmt2.setInt(2, rsvNo);
			pstmt2.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// 변경된 뷰 출력
		String view = "SELECT * FROM RSV_VIEW";
		try {
			pstmt = conn.prepareStatement(view);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int rsvNo2 = rs.getInt("RSV_NO");
				Date rsvDate2 = rs.getDate("RSV_DATE");
				String pName = rs.getString("P_NAME");
				String loca = rs.getString("LOCA");
				int payNo = rs.getInt("PAY_NO");
				String pthName = rs.getString("PTH_NAME");
				
				System.out.println("==== 변경된 내역 ====");
				System.out.println("예약 번호 : " + rsvNo2);
				System.out.println("예약일 : " + rsvDate2);
				System.out.println("상호명 : " + pName);
				System.out.println("가게 주소 : " + loca);
				System.out.println("결제 번호 : " + payNo);
				System.out.println("결제 수단 : " + pthName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
		}

		// 성공적으로 변경됐습니다. 출력
		System.out.println("예약을 성공적으로 변경하였습니다.");
	}
	
	
	//예약 취소
	public static void delRsv() {
		System.out.println("===== 예약 취소 =====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		//예약 취소 여부 받기
		System.out.println("예약을 취소하시겠습니까? (y/n) : ");
		String cancel = ObjController.scanStr();
			
		//데이터 수정
		String sql2 = "UPDATE RESERVATION SET CANCEL = UPPER(?) WHERE MEM_NO = ?";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;		
		ResultSet rs2 = null;
		try {
			String sql1 = "SELECT * FROM RSV_VIEW";
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int rsvNo = rs.getInt("RSV_NO");
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, cancel);
			pstmt2.setInt(2, rsvNo);
			pstmt2.executeUpdate();
			
			if(cancel.equals("y") || cancel.equals("Y")) {
				System.out.println("예약을 성공적으로 취소하였습니다.");
			}else {
				System.out.println("예약 취소 실패...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
