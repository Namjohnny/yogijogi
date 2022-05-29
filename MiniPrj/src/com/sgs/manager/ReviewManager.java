package com.sgs.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class ReviewManager {
	Scanner sc = new Scanner(System.in);
	
	public ReviewManager() {
		showList();
	}
	
	public static void showReviewColumns() {
		System.out.println("리뷰번호 | 회원번호 | 핫플번호 | 제목 | 평점 | 작성날짜 | 삭제여부");
	}
	
	public void showList() {
		while(true) {
			System.out.println("\n=====리뷰관리=====");
			System.out.println("1.리뷰조회");
			System.out.println("2.리뷰등록");
			System.out.println("3.리뷰수정");
			System.out.println("4.리뷰삭제");
			System.out.println("0.리뷰관리 종료");
			int n = sc.nextInt();
				 if(n==1) searchReview();
			else if(n==2) addReview();
			else if(n==3) modReview();
			else if(n==4) delReview();
			else if(n==0) {
				System.out.println("리뷰관리 종료");
				break;
			}
			else System.out.println("잘못입력되었습니다. 다시 선택해주세요");
		}
	}
	public void searchReview() {
		System.out.println("===잠시만 기다려주세요===\n");
		Connection conn = OracleDB.getOracleConnection();
		String sql = "SELECT * FROM REVIEW";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		showReviewColumns();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int rno = rs.getInt("R_NO");
				int memno = rs.getInt("MEM_NO");
				int pno = rs.getInt("PNO");
				String title = rs.getString("TITLE");
				int score = rs.getInt("SCORE");
				String review = rs.getString("REVIEW");
				String wdate = rs.getString("WDATE");
				String deleteyn = rs.getString("DELETE_YN");
				System.out.print(rno);
				System.out.print(" | ");
				System.out.print(memno);
				System.out.print(" | ");
				System.out.print(pno);
				System.out.print(" | ");
				System.out.print(title);
				System.out.print(" | ");
				System.out.print(score);
				System.out.print(" | ");
				System.out.print(wdate);
				System.out.print(" | ");
				System.out.println(deleteyn);
				System.out.println("리뷰 >> "+review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	}
	public void addReview() {
		System.out.println("===잠시만 기다려주세요===\n");
		Connection conn = OracleDB.getOracleConnection();
		String sql = "INSERT INTO REVIEW(R_NO, MEM_NO, PNO, TITLE, SCORE, REVIEW, DELETE_YN) "
				   + "VALUES(REVIEW_SEQ.NEXTVAL,?,?,?,?,?,?) ";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("리뷰번호는 자동할당됩니다");
			System.out.print("회원번호>> ");
			int memno = ObjController.scanInt();
			System.out.print("핫플번호>> ");
			int pno = ObjController.scanInt();
			System.out.print("제목>> ");
			String title = ObjController.scanStr();
			System.out.print("평점(0~5)>> ");
			int score = ObjController.scanInt();
			System.out.print("리뷰>> ");
			String review = ObjController.scanStr();

			
			pstmt.setInt(1, memno);
			pstmt.setInt(2, pno);
			pstmt.setString(3, title);
			pstmt.setInt(4, score);
			pstmt.setString(5, review);
			pstmt.setString(6, "N");
			
			result = pstmt.executeUpdate();
			if(result==1) System.out.println("리뷰등록 성공!!!");
			else System.out.println("리뷰등록 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
	}	
	public void modReview() {
		System.out.println("===잠시만 기다려주세요===\n");
		Connection conn = OracleDB.getOracleConnection();
		String sql1 = "UPDATE PAYMENT SET TITLE = ? WHERE R_NO = ?";	
		String sql2 = "UPDATE PAYMENT SET SCORE = ? WHERE R_NO = ?";
		String sql3 = "UPDATE PAYMENT SET REVIEW = ? WHERE R_NO = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			System.out.println("수정하실 리뷰번호를 말씀해주세요");
			System.out.print("리뷰번호>> ");
			int rno = ObjController.scanInt();
			while(true) {
				System.out.println("수정하실 사항을 선택해주세요");
				System.out.println("1.제목 | 2.평점 | 3.리뷰");
				int choice = ObjController.scanInt();
				if(choice == 1) {
					pstmt = conn.prepareStatement(sql1);
					System.out.print("제목>> ");
					String title = ObjController.scanStr();
					pstmt.setString(1, title);
					pstmt.setInt(2, rno);
					break;
				} else if(choice == 2) {
					pstmt = conn.prepareStatement(sql2);
					System.out.print("평점>> ");
					int score = ObjController.scanInt();
					pstmt.setInt(1, score);
					pstmt.setInt(2, rno);
					break;
				} else if(choice == 3) {
					pstmt = conn.prepareStatement(sql3);
					System.out.print("리뷰>> ");
					String review = ObjController.scanStr();
					pstmt.setString(1, review);
					pstmt.setInt(2, rno);
					break;
				} else {
					System.out.println("존재하지 않는 선택지입니다.");
					System.out.println("다시 선택해 주세요");
				}
			}
			result = pstmt.executeUpdate();
			if(result==1) System.out.println("리뷰수정 성공!!!");
			else System.out.println("리뷰수정 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
	public void delReview() {
		System.out.println("===잠시만 기다려주세요===\n");
		Connection conn = OracleDB.getOracleConnection();
		String sql = "UPDATE REVIEW SET DELETE_YN = 'Y' WHERE R_NO = ?";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("삭제하실 리뷰의 리뷰번호를 말씀해주세요");
			System.out.print("리뷰번호>> ");
			int payno = ObjController.scanInt();
			pstmt.setInt(1, payno);
			result = pstmt.executeUpdate();
			if(result==1) System.out.println("리뷰삭제 성공!!!");
			else System.out.println("리뷰삭제 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
}
