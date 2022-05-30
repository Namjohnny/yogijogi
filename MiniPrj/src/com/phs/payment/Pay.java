package com.phs.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yogijogi.member.Login;
import com.yogijogi.member.MyPage;
import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;


public class Pay {

	public static void payNow01(User user) {
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
		
		String sql3 = "UPDATE MEMBER SET RANK = 'V' WHERE MEM_NO = ? ";
		
		PreparedStatement pstmt3 = null;
		try {
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, com.yogijogi.member.User.LoginUserNo);
			int result = pstmt3.executeUpdate();
			if(result == 1) {
				user.setRank("V");
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
		
		String sql3 = "UPDATE RESERVATION SET PAY_NO = PAYMENT_PNO_SEQ.CURRVAL WHERE RSV_NO = ?";
		
		PreparedStatement pstmt3 = null;
		try {
			PreparedStatement pstmt4 = null;

            ResultSet rs = null;


            String sql4 = "SELECT * FROM RSV_VIEW";
            pstmt4 = conn.prepareStatement(sql4);
            rs = pstmt4.executeQuery();
            rs.next();
            int rsvNo = rs.getInt("RSV_NO");
                
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, rsvNo);
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
		String sql = "SELECT "
				+ "		 M.NICK"
				+ "    , P.PAY_NO"
				+ "    , P.PAYCON"
				+ "    , P.AMOUNT"
				+ "    , PM.PNAME"
				+ "    , P.PAY_YN"
				+ "    , P.PAY_DATE"
				+ " FROM MEMBER M"
				+ " JOIN PAYMENT P"
				+ " ON M.MEM_NO = P.MEM_NO"
				+ " JOIN PAYMETHOD PM"
				+ " ON P.PAY_NO = PM.PM_NO"
				+ " WHERE P.MEM_NO = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com.yogijogi.member.User.LoginUserNo);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.print("닉네임");
			System.out.print(" | ");
			System.out.print("결제번호");
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
				String nick = rs.getString("NICK");
				int payNo = rs.getInt("PAY_NO");
				String payCon = rs.getString("PAYCON");
				int amt = rs.getInt("AMOUNT");
				String pName = rs.getString("PNAME");
				String pCancel = rs.getString("PAY_YN");
				Timestamp pd = rs.getTimestamp("PAY_DATE");
				
				System.out.print(nick);
				System.out.print(" | ");
				System.out.print(payNo);
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
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			com.yogijogi.obj.OracleDB.close(conn);
			com.yogijogi.obj.OracleDB.close(pstmt);
		}
		
		System.out.println("===== 결제 취소 선택 =====");
		System.out.println("1. 결체 취소하기");
		System.out.println("2. 돌아가기");
		
		int pageNum = ObjController.scanInt();
		if(pageNum == 1) {
			payCancel(payCancelChoice());
		}else if(pageNum == 2) {
			return;
		}else {
			System.out.println("잘못된 입력입니다. 돌아갑니다.");
			return;
		}
		
		
	}
	
	public int payCancelChoice() {
		
		System.out.println("결제를 취소하실 항목의 결제번호를 입력하시오.");
		int userInput = ObjController.scanInt();
		return userInput;
		
	}
	
	
	public void payCancel(int a) {
		MyPage mp = new MyPage();
		boolean isNotValue = false;
		while(!isNotValue) {
				System.out.println("결제를 취소 하시겠습니까?");
				System.out.println("입력하시오. (Y/N)");
				String userInput = com.yogijogi.obj.ObjController.scanStr();
			if(userInput.equalsIgnoreCase("Y")) {
				PreparedStatement pstmt1 = null, pstmt2 = null;
				Connection conn = com.yogijogi.obj.OracleDB.getOracleConnection();
				String sql1 = "UPDATE PAYMENT SET PAY_YN = 'Y' WHERE MEM_NO = ? AND PAY_NO = ?";
				String sql2 = "UPDATE RESERVATION SET CANCEL = 'Y' WHERE MEM_NO = ?";
				try {
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setInt(1, com.yogijogi.member.User.LoginUserNo);
					pstmt1.setInt(2, a);
					pstmt1.executeUpdate();
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, com.yogijogi.member.User.LoginUserNo);
					pstmt2.executeUpdate();
					int result = pstmt2.executeUpdate();
					if(result == 1) {
						System.out.println("결제가 취소되었습니다.");
						System.out.println("이전 화면으로 돌아갑니다.");
						payList();
					}else {
						System.out.println("오류 발생.");
						System.out.println("이전 화면으로 돌아갑니다.");
						payList();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					com.yogijogi.obj.OracleDB.close(conn);
					com.yogijogi.obj.OracleDB.close(pstmt1);
					com.yogijogi.obj.OracleDB.close(pstmt2);
				}
				isNotValue = true;
			}else if(userInput.equalsIgnoreCase("N")) {
				System.out.println("이전메뉴로 돌아갑니다.");
				payList();
			}else {
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}























































