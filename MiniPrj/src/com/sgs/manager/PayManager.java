package com.sgs.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class PayManager {
	Scanner sc = new Scanner(System.in);
	
	public void showList() {
		while(true) {
			System.out.println("=====결제관리=====");
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
		String sql = "SELECT * FROM PAYMENT WHERE PAY_YN = 'N'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Columns.showPayColumns();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int payno = rs.getInt("PAY_NO");
				int memno = rs.getInt("MEM_NO");
				String paycon = rs.getString("PAYCON");
				int amount = rs.getInt("AMOUNT");
				String payyn = rs.getString("PAY_YN");
				Date paydate = rs.getDate("PAY_DATE");
				System.out.print(payno);
				System.out.print(" | ");
				System.out.print(memno);
				System.out.print(" | ");
				System.out.print(paycon);
				System.out.print(" | ");
				System.out.print(amount);
				System.out.print(" | ");
				System.out.print(payyn);
				System.out.print(" | ");
				System.out.print(paydate);
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
		String sql = "INSERT INTO PAYMENT(PAY_NO, MEM_NO, PAYCON, AMOUNT) "
				   + "VALUES(PAYMENT_PNO_SEQ.NEXTVAL,?,?,?) ";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("결제번호는 자동할당됩니다");
			System.out.print("회원번호>> ");
			String memno = ObjController.scanStr();
			System.out.print("결제내용>> ");
			String paycon = ObjController.scanStr();
			System.out.print("결제금액>> ");
			int amount = ObjController.scanInt();
			
			pstmt.setString(1, memno);
			pstmt.setString(2, paycon);
			pstmt.setInt(3, amount);
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			if(result==1) System.out.println("결제추가 성공!!!");
			else System.out.println("결제추가 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
	}	
	public void modPay() {
		System.out.println("===잠시만 기다려주세요===\n");
		Connection conn = OracleDB.getOracleConnection();
		String sql = "UPDATE PAYMENT SET ? = ? WHERE PAY_NO = ?";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("수정하실 결제번호를 말씀해주세요");
			System.out.print("결제번호>> ");
			int payno = ObjController.scanInt();
			pstmt.setInt(3, payno);
			while(true) {
				System.out.println("수정하실 사항을 선택해주세요");
				System.out.println("1.회원번호 | 2.결제내용 | 3.결제금액");
				int choice = ObjController.scanInt();
				if(choice == 1) {
					System.out.print("회원번호>> ");
					int memno = ObjController.scanInt();
					pstmt.setString(1,"MEM_NO");
					pstmt.setInt(2,memno);
					break;
				} else if(choice == 2) {
					System.out.print("결제내용>> ");
					String paycon = ObjController.scanStr();
					pstmt.setString(1,"PAYCON");
					pstmt.setString(2,"'"+paycon+"'");
					break;
				} else if(choice == 3) {
					System.out.print("결제금액>> ");
					int amount = ObjController.scanInt();
					pstmt.setString(1,"AMOUNT");
					pstmt.setInt(2,amount);
					break;
				} else {
					System.out.println("존재하지 않는 선택지입니다.");
					System.out.println("다시 선택해 주세요");
				}
			}
			result = pstmt.executeUpdate();
			if(result==4) System.out.println("결제수정 성공!!!");
			else System.out.println("결제수정 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
	public void delPay() {
		System.out.println("===잠시만 기다려주세요===\n");
		Connection conn = OracleDB.getOracleConnection();
		String sql = "UPDATE PAYMENT SET PAY_YN = 'Y' WHERE PAY_NO = ?";		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("취소하실 결제의 결제번호를 말씀해주세요");
			System.out.print("결제번호>> ");
			int payno = ObjController.scanInt();
			pstmt.setInt(1, payno);
			result = pstmt.executeUpdate();
			if(result==1) System.out.println("결제취소 성공!!!");
			else System.out.println("결제취소 실패...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
	}
}
