package com.yogijogi.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class Join {
	
	public boolean joinView() {
		System.out.println("=====회원가입=====");
		System.out.print("ID : ");
		String scanId = ObjController.scanStr();
		System.out.print("PW : ");
		String scanPwd = ObjController.scanStr();
		System.out.print("성명 : ");
		String scanName = ObjController.scanStr();
		System.out.print("닉네임 : ");
		String scanNick = ObjController.scanStr();
		System.out.println("생년월일 8자리를 입력해주세요. (ex:19950627)");
		System.out.print(">> ");
		String scanBirth = ObjController.scanStr();
		System.out.println("성별 (M || F)");
		System.out.print(">> ");
		String scanGen = ObjController.scanStr();
		
		if(scanPwd.length() < 8) {
			// 비밀번호 8글자 미만일 시 가입 실패
			System.out.println("비밀번호는 8글자 이상이어야 합니다!");
			return false;
		}
		if(!(scanBirth.matches("[+-]?\\d*(\\.\\d+)?") && (scanBirth.length() == 8))) {
			// 주민번호를 숫자 8자리가 아니게 입력했다면 가입 실패
			System.out.println("생년월일 8자리로 입력해주세요!");
			return false;
		}
		if(!(scanGen.equals("M") || scanGen.equals("F"))) {
			// 성별에 M, F, m, f를 입력하지 않았다면 가입 실패
			System.out.println("M 또는 F만 입력 가능합니다!");
			return false;
		}
		
		if(!(overlapID(scanId)||overlapNick(scanNick))) {
		userJoin(scanId, scanPwd, scanName, scanNick, scanBirth, scanGen);
		System.out.println("가입이 완료되었습니다!");
		return true;
		}
		
		return false;
		
	}
	
	public void userJoin(String scanId, String scanPwd, String scanName, String scanNick, String scanBirth, String scanGen) {
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO MEMBER(MEM_NO, ID, PWD, UNAME, NICK, BIRTH_DATE, GENDER) VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, TO_DATE(?), ?)");
			pstmt.setString(1, scanId);
			pstmt.setString(2, scanPwd);
			pstmt.setString(3, scanName);
			pstmt.setString(4, scanNick);
			pstmt.setString(5, scanBirth);
			pstmt.setString(6, scanGen);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("SQL 관련 에러 !!!");
		} finally {
			//자원 정리
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
	}
	
	public boolean overlapID(String userId) {
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("중복된 아이디입니다 !!!");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 관련 에러 !!!");
		} finally {
			//자원 정리
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return false;
		
	}
	
public boolean overlapNick(String userNick) {
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE NICK = ?");
			pstmt.setString(1, userNick);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("중복된 닉네임입니다 !!!");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 관련 에러 !!!");
		} finally {
			//자원 정리
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return false;
		
	}

}
