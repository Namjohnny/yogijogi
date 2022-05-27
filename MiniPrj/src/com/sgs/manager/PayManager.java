package com.sgs.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class PayManager {
	Scanner sc = new Scanner(System.in);
	
	public void showList() {
		System.out.println("=====결제관리=====");
		while(true) {
			System.out.println("1.결제조회");
			System.out.println("2.결제추가");
			System.out.println("3.결제수정");
			System.out.println("4.결제삭제");
			System.out.println("0.결제관리 종료");
			int n = sc.nextInt();
				 if(n==1) searchPay();
			else if(n==2) addPay();
			else if(n==3) modPay();
			else if(n==4) delPay();
			else if(n==0) {
				System.out.println("결제관리 종료");
				break;
			}
			else System.out.println("잘못입력되었습니다. 다시 선택해주세요");
		}
	}
	public void searchPay() {
		Connection conn = OracleDB.getOracleConnection();
		String sql = "SELECT * FROM PAYMENT";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Columns.showPayColumns();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pno = rs.getInt("PNO");
				String nick = rs.getString("NICK");
				String paycon = rs.getString("PAYCON");
				int amount = rs.getInt("AMOUNT");
				System.out.print(pno);
				System.out.print(" | ");
				System.out.print(nick);
				System.out.print(" | ");
				System.out.print(paycon);
				System.out.print(" | ");
				System.out.print(amount);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	}
	public void addPay() {
		Connection conn = OracleDB.getOracleConnection();
		String sql = "INSERT INTO PAYMENT VALUES(?,?,?,?) ";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("결제번호>> ");
			int pno = ObjController.scanInt();
			System.out.println("닉네임>> ");
			String nick = ObjController.scanStr();
			System.out.println("결제내용>> ");
			String paycon = ObjController.scanStr();
			System.out.println("결제금액>> ");
			int amount = ObjController.scanInt();
			
			pstmt.setInt(1, pno);
			pstmt.setString(2, nick);
			pstmt.setString(3, paycon);
			pstmt.setInt(4, amount);
			
			result = pstmt.executeUpdate();
			if(result==4) System.out.println("결제추가 성공!!!");
			else System.out.println("결제추가 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
	}	
	public void modPay() {
		Connection conn = OracleDB.getOracleConnection();
		String sql = "UPDATE PAYMENT SET ? = ? WHERE PNO = ?";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("수정하실 결제번호를 말씀해주세요");
			System.out.print("결제번호>> ");
			int pno = ObjController.scanInt();
			pstmt.setInt(3, pno);
			while(true) {
				System.out.println("수정하실 칼럼을 선택해주세요");
				System.out.println("1.닉네임 | 2.결제내용 | 3.결제금액");
				int choice = ObjController.scanInt();
				if(choice == 1) {
					System.out.print("닉네임>> ");
					String nick = ObjController.scanStr();
					pstmt.setString(1, "NICK");
					pstmt.setString(2, nick);
					break;
				} else if(choice == 2) {
					System.out.print("결제내용>> ");
					String paycon = ObjController.scanStr();
					pstmt.setString(1, "PAYCON");
					pstmt.setString(2, paycon);
					break;
				} else if(choice == 3) {
					System.out.print("결제금액>> ");
					int amount = ObjController.scanInt();
					pstmt.setString(1, "AMOUNT");
					pstmt.setInt(2, amount);
					break;
				} else {
					System.out.println("존재하지 않는 선택지입니다.");
					System.out.println("다시 선택해 주세요");
				}
			}
			result = pstmt.executeUpdate();
			if(result==4) System.out.println("결제추가 성공!!!");
			else System.out.println("결제추가 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
	public void delPay() {
		Connection conn = OracleDB.getOracleConnection();
		String sql = "DELETE FROM PAYMENT WHERE PNO = ? ";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("결제번호>> ");
			int pno = ObjController.scanInt();
			
			pstmt.setInt(1, pno);
			
			result = pstmt.executeUpdate();
			if(result==4) System.out.println("결제삭제 성공!!!");
			else System.out.println("결제삭제 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
}
