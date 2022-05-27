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
		System.out.println("=====ȸ������=====");
		System.out.print("ID : ");
		String scanId = ObjController.scanStr();
		System.out.print("PW : ");
		String scanPwd = ObjController.scanStr();
		System.out.print("���� : ");
		String scanName = ObjController.scanStr();
		System.out.print("�г��� : ");
		String scanNick = ObjController.scanStr();
		System.out.println("������� 8�ڸ��� �Է����ּ���. (ex:19950627)");
		System.out.print(">> ");
		String scanBirth = ObjController.scanStr();
		System.out.println("���� (M || F)");
		System.out.print(">> ");
		String scanGen = ObjController.scanStr();
		
		if(scanPwd.length() < 8) {
			// ��й�ȣ 8���� �̸��� �� ���� ����
			System.out.println("��й�ȣ�� 8���� �̻��̾�� �մϴ�!");
			return false;
		}
		if(!(scanBirth.matches("[+-]?\\d*(\\.\\d+)?") && (scanBirth.length() == 8))) {
			// �ֹι�ȣ�� ���� 8�ڸ��� �ƴϰ� �Է��ߴٸ� ���� ����
			System.out.println("������� 8�ڸ��� �Է����ּ���!");
			return false;
		}
		if(!(scanGen.equals("M") || scanGen.equals("F"))) {
			// ������ M, F, m, f�� �Է����� �ʾҴٸ� ���� ����
			System.out.println("M �Ǵ� F�� �Է� �����մϴ�!");
			return false;
		}
		
		if(!(overlapID(scanId)||overlapNick(scanNick))) {
		userJoin(scanId, scanPwd, scanName, scanNick, scanBirth, scanGen);
		System.out.println("������ �Ϸ�Ǿ����ϴ�!");
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
			System.out.println("SQL ���� ���� !!!");
		} finally {
			//�ڿ� ����
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
				System.out.println("�ߺ��� ���̵��Դϴ� !!!");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL ���� ���� !!!");
		} finally {
			//�ڿ� ����
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
				System.out.println("�ߺ��� �г����Դϴ� !!!");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL ���� ���� !!!");
		} finally {
			//�ڿ� ����
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return false;
		
	}

}
