package com.yogijogi.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class MyPage {
	
	public void showPage(User user) {
		boolean myPageOut = false;
		
		while(!myPageOut) {
			System.out.println("===== 마이페이지 =====");
			System.out.println("1. 개인정보 조회");
			System.out.println("2. 로그아웃");
			System.out.println("3. 예약 내역");
			System.out.println("4. 결제 내역");
			System.out.println("5. 리뷰 확인");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 나가기");
			System.out.print("번호 입력>> ");
			int pageNum = ObjController.scanInt();
			
			switch(pageNum) {
			case 1:
				//개인정보 조회
				System.out.println(user);
				break;
			case 2:
				//로그아웃
				logout();
				break;
			case 3:
				//예약 내역 확인
				break;
			case 4:
				//결제 내역 확인
				break;
			case 5:
				//리뷰 확인
				break;
			case 6:
				//회원 탈퇴
				memDrop(user.getMemNo());
				break;
			case 7:
				//마이페이지에서 나가기
				myPageOut = true;
				break;
			default:
				System.out.println("잘못 입력하였습니다 !!!");
			}	
		}
		
	}


	//로그아웃
	private void logout() {
		User.LoginUserNo = -1;
		
		System.out.println("로그아웃 되었습니다!");
	}
	
	//예약내역 확인
	
	
	
	
	//회원 탈퇴
	private void memDrop(int memNo) {
		System.out.println("정말 탈퇴하시겠습니까? ('예' || '아니오')");
		System.out.print(">> ");
		
		
		String dropCheck = ObjController.scanStr();
		
		switch(dropCheck) {
		case "예":
			Connection conn = OracleDB.getOracleConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("UPDATE MEMBER SET DROP_YN = 'Y' WHERE MEM_NO = ?");
				pstmt.setInt(1, memNo);
				rs = pstmt.executeQuery();
				
			} catch (SQLException e) {
				System.out.println("SQL 관련 에러 !!!");
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}	
			System.out.println("회원 탈퇴가 완료되었습니다!");
		case "아니오":
			break;
		default:
			System.out.println("잘못 입력하였습니다 !!!");
		}
		
		
	}
	

}
