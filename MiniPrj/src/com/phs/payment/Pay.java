package com.phs.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Pay {

	public static void payNow01() {
		System.out.println("============ VIP 등급 결제 페이지 ============");
		System.out.println("결제 하실 금액은 30,000원 입니다.");
		
		String str = com.phs.payMethod.ChoicePayMethod.PayNow();
		
		Connection conn = com.yogijogi.obj.OracleDB.getOracleConnection();
		
		String sql1 = "INSERT INTO PAYMENT(PAY_NO, MEM_NO, PAYCON, AMOUNT) "
				+ "VALUES (PAYMENT_PNO_SEQ.NEXTVAL,?,?,30000)";
		
		PreparedStatement pstmt1 = null;
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, com.yogijogi.member.User.LoginUserNo);
			pstmt1.setString(2, "등급결제");
			int result = pstmt1.executeUpdate();
			if(result == 1) {
				System.out.println("등급결제가 완료되었습니다.");
			}else {
				System.out.println("결제가 실패되었습니다.");
				System.out.println("결제창으로 돌아갑니다.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(pstmt1);
		}
		
		String sql2 = "INSERT INTO PAYMETHOD(PM_NO, PNAME) "
				+ "VALUES (PAYMETHOD_NO_SEQ.NEXTVAL,?)";
		
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, str);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(pstmt2);
		}
		
		String sql3 = "INSERT INTO MEMBER(RANK) "
				+ "VALUES (V) WHERE MEM_NO = ?";
		
		PreparedStatement pstmt3 = null;
		try {
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, com.yogijogi.member.User.LoginUserNo);
			int result = pstmt3.executeUpdate();
			if(result == 1) {
				System.out.println("VIP 등급이 설정되었습니다.");
			}else {
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(pstmt3);
			com.yogijogi.obj.OracleDB.close(conn);
		}
		
		
		
		
		
		
	}
	public static void payNow02() {
		System.out.println("============ 예약 결제 페이지 ============");
		System.out.println("결제 하실 금액은 20,000 (보증금) 입니다.");
		
		String str = com.phs.payMethod.ChoicePayMethod.PayNow();
		
		Connection conn = com.yogijogi.obj.OracleDB.getOracleConnection();
		
		String sql1 = "INSERT INTO PAYMENT(PAY_NO, MEM_NO, PAYCON, AMOUNT) "
				+ "VALUES (PAYMENT_PNO_SEQ.NEXTVAL,?,?,20000)";
		
		PreparedStatement pstmt1 = null;
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, com.yogijogi.member.User.LoginUserNo);
			pstmt1.setString(2, "예약결제");
			int result = pstmt1.executeUpdate();
			if(result == 1) {
				System.out.println("예약결제가 완료되었습니다.");
			}else {
				System.out.println("결제가 실패되었습니다.");
				System.out.println("결제창으로 돌아갑니다.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(pstmt1);
		}
		
		String sql2 = "INSERT INTO PAYMETHOD(PM_NO, PNAME) "
				+ "VALUES (PAYMETHOD_NO_SEQ.NEXTVAL,?)";
		
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, str);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(pstmt2);
		}
		
		String sql3 = "INSERT INTO RESERVATION(PAY_NO) "
				+ "VALUES (PAYMENT_PNO_SEQ.CURRVAL) ";
		
		PreparedStatement pstmt3 = null;
		try {
			pstmt3 = conn.prepareStatement(sql3);
			int result = pstmt3.executeUpdate();
			if(result == 1) {
				System.out.println("메인 화면으로 돌아갑니다.");
			}else {
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(pstmt3);
			com.yogijogi.obj.OracleDB.close(conn);
		}
		
		
		
		
		
		
	}
	
}























































