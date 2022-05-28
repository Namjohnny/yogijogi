package com.phs.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yogijogi.member.Login;
import com.yogijogi.member.MyPage;
import com.yogijogi.member.User;


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
		
		String sql3 = "UPDATE MEMBER SET RANK = V WHERE MEM_NO = ? ";
		
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
	
	
	public void payList() {
		
		System.out.println("결제 내역 확인");
		Connection conn = com.yogijogi.obj.OracleDB.getOracleConnection();
		String sql = "SELECT"
				+ "M.NICK 닉네임"
				+ ", P.PAYCON"
				+ ", P.AMOUNT"
				+ ", PM.PNAME"
				+ ", P.PAY_YN"
				+ ", P.PAY_DATE"
				+ "FROM MEMBER M"
				+ "JOIN PAYMENT P"
				+ "ON M.MEM_NO = P.MEM_NO"
				+ "JOIN PAYMETHOD PM"
				+ "ON P.PAY_NO = PM.PM_NO"
				+ "WHERE P.MEM_NO = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com.yogijogi.member.User.LoginUserNo);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.print("닉네임");
			System.out.print(" | ");
			System.out.print("결제내용");
			System.out.print(" | ");
			System.out.print("결제금액");
			System.out.print(" | ");
			System.out.print("결제수단");
			System.out.print(" | ");
			System.out.print("결제취소여부");
			System.out.print(" | ");
			System.out.print("결제날짜");
			System.out.println("\n --------------------------------------------");
			
			while(rs.next()) {
				String nick = rs.getString("M.NICK");
				String payCon = rs.getString("P.PAYCON");
				int amt = rs.getInt("P.AMOUNT");
				String pName = rs.getString("PM.PNAME");
				String pCancel = rs.getString("P.PAY_YN");
				Timestamp pd = rs.getTimestamp("P.PAY_DATE");
				
				System.out.print(nick);
				System.out.print(" | ");
				System.out.print(payCon);
				System.out.print(" | ");
				System.out.print(amt);
				System.out.print(" | ");
				System.out.print(pName);
				System.out.print(" | ");
				System.out.print(pCancel);
				System.out.print(" | ");
				System.out.print(pd);
				System.out.println();
				
				// 스캐너 사용해서 번호입력받고
				// 번호를 변수에 저장해준다음
				// payCancel 에 변수 넘겨주면
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(conn);
			com.yogijogi.obj.OracleDB.close(pstmt);
		}
		
		if(payCancel()) {
			return;
		}
		
	}
	
	
	public boolean payCancel() {
		
		MyPage mp = new MyPage();
		boolean isNotValue = false;
		while(!isNotValue) {
				System.out.println("결제를 취소 하시겠습니까?");
				System.out.println("입력하시오. (Y/N)");
				String userInput = com.yogijogi.obj.ObjController.scanStr();
			if(userInput.equalsIgnoreCase("Y")) {
				PreparedStatement pstmt1 = null, pstmt2 = null;
				Connection conn = com.yogijogi.obj.OracleDB.getOracleConnection();
				String sql1 = "UPDATE PAYMENT SET PAY_YN = Y WHERE MEM_NO = ?";
				String sql2 = "UPDATE RESERVATION SET CANCEL = Y WHERE MEM_NO = ?";
				try {
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setInt(1, com.yogijogi.member.User.LoginUserNo);
					pstmt1.executeUpdate();
					pstmt2 = conn.prepareStatement(sql1);
					pstmt2.setInt(1, com.yogijogi.member.User.LoginUserNo);
					pstmt2.executeUpdate();
					int result = pstmt2.executeUpdate();
					if(result == 1) {
						System.out.println("결제가 취소되었습니다.");
						System.out.println("메인 화면으로 돌아갑니다.");
						return true;
					}else {
						System.out.println("오류 발생.");
						System.out.println("메인 화면으로 돌아갑니다.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					com.yogijogi.obj.OracleDB.close(conn);
					com.yogijogi.obj.OracleDB.close(pstmt1);
					com.yogijogi.obj.OracleDB.close(pstmt2);
				}

				isNotValue = true;
				// 결제 안내페이지로 이동
			}else if(userInput.equalsIgnoreCase("N")) {
				System.out.println("이전메뉴로 돌아갑니다.");
				return true;
			}else {
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}























































