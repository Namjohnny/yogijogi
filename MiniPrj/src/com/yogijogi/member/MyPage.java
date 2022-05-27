package com.yogijogi.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class MyPage {
	
	public void showPage(User user) {
		System.out.println("===== ���������� =====");
		System.out.println("1. �������� ��ȸ");
		System.out.println("2. �α׾ƿ�");
		System.out.println("3. ȸ�� Ż��");
		System.out.print("��ȣ �Է� >> ");
		
		int pageNum = ObjController.scanInt();
		switch(pageNum) {
			case 1:
				user.toString();
				break;
			case 2:
				logout();
				break;
			case 3:
				memDrop(user.getMemNo());
				break;
			default:
				System.out.println("�߸� �Է��Ͽ����ϴ� !!!");
		}
		
	}

	private void memDrop(int memNo) {
		System.out.println("���� Ż���Ͻðڽ��ϱ�? ('��' || '�ƴϿ�')");
		System.out.print(">> ");
		
		
		String dropCheck = ObjController.scanStr();
		
		switch(dropCheck) {
			case "��":
				Connection conn = OracleDB.getOracleConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = conn.prepareStatement("UPDATE MEMBER SET DROP_YN = 'Y' WHERE MEM_NO = ?");
					pstmt.setInt(1, memNo);
					rs = pstmt.executeQuery();
					
				} catch (SQLException e) {
					System.out.println("SQL ���� ���� !!!");
				} finally {
					//�ڿ� ����
					OracleDB.close(conn);
					OracleDB.close(pstmt);
					OracleDB.close(rs);
				}	
			case "�ƴϿ�":
				break;
			default:
				System.out.println("�߸� �Է��Ͽ����ϴ� !!!");
		}
		
		
	}

	private void logout() {
		User.LoginUserNo = -1;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�!");
	}
	

}
