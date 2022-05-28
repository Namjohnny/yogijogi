package com.cay.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "SELECT * FROM RSV_LIST WHERE CANCEL = 'N' AND MEM_NO = ? ORDER BY RSVDATE DESC";
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
			System.out.print("예약일");	
			System.out.println("\n--------------------------------");

			System.out.println();
			while(rs.next()) {
				int rsvNo = rs.getInt("RSV_NO");
				String pName = rs.getString("PNAME");
				String rsvDate = rs.getString("RSV_DATE");
				
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
	}

	//예약하기
	public static void addRsv() {
		if(User.LoginUserNo == 0) { //유저넘버 0은 없으니까 접근 못하게 리턴함. 
			System.out.println("로그인한 유저만 예약할 수 있습니다.");
			return;
		}
		
		System.out.println("==== 예약 페이지 ====");
		System.out.print("예약 날짜 및 시간(24h) (yy-mm-dd hh:mi) : ");		
		String rsvDate = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO RESERVATION(RSV_NO, MEM_NO, P_NO, RSV_DATE, CANCEL)"
				+ "VALUES(RESERVATION_NO_SEQ.NEXTVAL, ?, ?, to_date(?, 'yyyy/mm/dd hh24:mi'), 'N')";
		
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
		String sql = "SELECT * FROM RSV_VIEW";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int rsvNo = rs.getInt("RSV_NO");
				int rsvDate = rs.getInt("RSV_DATE");
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
				User user = null;
				ResultSet rs1 = null;
	
				try {
					if(rs1.next()) {
						int memNo = rs1.getInt("MEM_NO");
						String id = rs1.getString("ID");
						String pwd = rs1.getString("PWD");
						String uname = rs1.getString("UNAME");
						String nick = rs1.getString("NICK");
						String rank = rs1.getString("RANK");
						String dropYN = rs1.getString("DROP_YN");
						user = new User(memNo, id, pwd, uname, nick, rank, dropYN);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				MyPage.showPage(user); break;
			default : System.out.println("다시 선택하세요.");
		}
		
	}
	
	//예약 변경
	public static void modRsv() {
		System.out.println("===== 예약 변경 =====");
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		//변경 날짜 입력 받기
		System.out.print("변경 원하는 날짜와 시간 : ");
		String rsvDate = ObjController.scanStr();
		
		//데이터 수정
		String sql = "UPDATE RESERVATION SET PNAME = ? WHERE RSVDATE = ?";
		
		try {
			pstmt.setInt(1, User.LoginUserNo);
			pstmt.setString(2, rsvDate);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// 변경된 뷰 출력
		String view = "SELECT * FROM RSV_VIEW;";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int rsvNo = rs.getInt("RSV_NO");
				int rsvDate2 = rs.getInt("RSV_DATE");
				String pName = rs.getString("P_NAME");
				String loca = rs.getString("LOCA");
				int payNo = rs.getInt("PAY_NO");
				String pthName = rs.getString("PTH_NAME");
				
				System.out.println("==== 변경된 내역 ====");
				System.out.println("예약 번호 : " + rsvNo);
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
		String cancle = ObjController.scanStr();
			
		//데이터 수정
		String sql = "UPDATE RESERVATION SET CANCLE = ? WHERE MEM_NO = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();

			pstmt.setString(1, cancle);
			pstmt.setInt(2, User.LoginUserNo);
			
			int result = pstmt.executeUpdate();
			
			if(cancle.equals("y") || cancle.equals("Y")) {
				System.out.println("예약을 성공적으로 취소하였습니다.");
			}else {
				System.out.println("예약을 취소하지 못했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
