package com.yogijogi.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class Login {
	// page.showPage(login.loginView());

	public User loginView() {
		//�α��� ȭ��
		
		//�α���â
		System.out.println("===== �α��� =====");
		System.out.print("ID : ");
		String id = ObjController.scanStr();
		System.out.print("PW : ");
		String pwd = ObjController.scanStr();
		User user = checkUser(id, pwd);
		
		//���Ե� �������� Ȯ��
		if(user != null) {
			User.LoginUserNo = user.getNo();
			System.out.println("�α��� ����!");
		}else {
			System.out.println("�α��� ����...");
		}
		
		return user;
	}
	
	public User checkUser(String scanId, String scanPwd) {
		//���� Ȯ��
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND DROP_YN = 'N'");
			pstmt.setString(1, scanId);
			pstmt.setString(2, scanPwd);
			rs = pstmt.executeQuery();	// ��������� �� rs �� ����
			// ���� ��������� Ŀ���� 0��ĭ(==����ִ� ����)�� ����Ű�� ���� 
			//������տ���, ���� Ŀ���� ����Ű�� row �� Į������ MEM_NO �� ���� int Ÿ�� �����͸� �о��//�� ������ 0��row�ε� .... �Ф� ���� �Ф�
			//�̷��� �ʿ��մϴ�
			
			if(rs.next()) {
				int memNo = rs.getInt("MEM_NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String uname = rs.getString("UNAME");
				String nick = rs.getString("NICK");
				String rank = rs.getString("RANK");
				String dropYN = rs.getString("DROP_YN");
				User user = new User(memNo, id, pwd, uname, nick, rank, dropYN);
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//�ڿ� ����
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return null;
		
	}
	
	
//	public void rankUp(String userId) {
//		//��� �� -> ���� ����
//		//���� ������ DB�� RANK�� Ư�� ���� �̻��̸� ���� ����
//		
//		Connection conn = OracleDB.getOracleConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = conn.prepareStatement("UPDATE MEMBER SET RANK = RANK+1 WHERE ID = ?");
//			pstmt.setString(1, userId);
//			rs = pstmt.executeQuery();
//					
//			
//		} catch (SQLException e) {
//			System.out.println("SQL ���� ���� !!!");
//		} finally {
//			//�ڿ� ����
//			OracleDB.close(conn);
//			OracleDB.close(pstmt);
//			OracleDB.close(rs);
//		}
//		
//	}

}