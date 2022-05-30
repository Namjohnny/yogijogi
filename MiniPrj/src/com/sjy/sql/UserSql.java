package com.sjy.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class UserSql {
	
	
	
//	public static void main(String[] args) {
//		numCh
//	}
	
	//회원번호 확인 체크
	public int numCheck(int num) {
		String chSelect = "SELECT MEM_NO FROM MEMBER WHERE MEM_NO = ?";
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			pstmt = conn.prepareStatement(chSelect);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				return 1;// 번호있음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}

		return -1;// 번호없음
	}//numCheck()
	
	//아이디 중복 체크
	public int idCheck(String id) {
		String chSelect = "SELECT MEM_NO FROM MEMBER WHERE ID = ?";
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(chSelect);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				return 1;// id있음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}

		return -1;// 번호없음
	}//idCheck()
	

	public void searchAll() {
		//모든 회원 보여주기
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "SELECT * FROM MEMBER WHERE DROP_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			showUIf(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
		
	}
	
	public void search(int num) {
		//특정 회원 정보 보여주기
		int numCh = numCheck(num);
		
		
		if(numCh == 1){
			Connection conn = OracleDB.getOracleConnection();
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			String sql = "SELECT * FROM MEMBER WHERE MEM_NO  =? and DROP_YN='N' ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs =pstmt.executeQuery();
				showUIf(rs);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else System.out.println("없는 회원번호입니다.");
		
	}
	
	public void modUser() {
		System.out.print("수정 회원번호:");
		int mNum = ObjController.scanInt();
		// 번호에맞는 회원정보 불러오기
		search(mNum);
		
		// 수정할 정보 입력
		// 입력받은 데이터 update
		boolean resultUp = modUserIf(mNum);
		if (resultUp) {
			System.out.println("수정완료");
		}else {System.out.println("수정실패");}
	}
	
	public boolean modUserIf(int mNum) {
		// 수정할 정보 입력
		// 아이디 패스워드 이름 닉네임 생일 성별
		System.out.println("=====정보수정=====");
		System.out.print("ID : ");
		String modId = ObjController.scanStr();
		int idCh = idCheck(modId);
		if (idCh == 1) {
			System.out.println("아이디 중복");
			return false;
		}
		System.out.print("PW : ");
		String modPwd = ObjController.scanStr();
		if(modPwd.length() < 8) {
			// 비밀번호 8글자 미만일 시 가입 실패
			System.out.println("비밀번호는 8글자 이상이어야 합니다!");
			return false;
		}
		
		System.out.print("성명 : ");
		String modName = ObjController.scanStr();
		
		System.out.print("닉네임 : ");
		String modNick = ObjController.scanStr();
		
		System.out.println("생년월일 8자리를 입력해주세요. (ex:19950627)");
		System.out.print(">> ");
		String modBirth = ObjController.scanStr();
		if(!(modBirth.matches("[+-]?\\d*(\\.\\d+)?") && (modBirth.length() == 8))) {
			// 주민번호를 숫자 8자리가 아니게 입력했다면 가입 실패
			System.out.println("생년월일 8자리로 입력해주세요!");
			return false;
		}
		
		System.out.println("성별 (M || F)");
		System.out.print(">> ");
		String modGen = ObjController.scanStr();
		
		
		
		if(!(modGen.equals("M") || modGen.equals("F"))) {
			// 성별에 M, F, m, f를 입력하지 않았다면 가입 실패
			System.out.println("M 또는 F만 입력 가능합니다!");
			return false;
		}
		Connection conn =OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET ID=?, PWD=?, UNAME=?, NICK=?, BIRTH_DATE=?, GENDER=? WHERE MEM_NO=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modId );
			pstmt.setString(2,modPwd );
			pstmt.setString(3,modName );
			pstmt.setString(4,modNick );
			pstmt.setString(5,modBirth );
			pstmt.setString(6, modGen);
			pstmt.setInt(7, mNum);
			int result = pstmt.executeUpdate();
			
			if (result == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {OracleDB.close(conn);OracleDB.close(pstmt);}
		return false;
		
		
		
		
		
	}
	
	public void delUser(int memNo) {
		System.out.println("정말 탈퇴하시겠습니까? ('예' || '아니오')");
		System.out.print(">> ");
		
		
		String dropCheck = ObjController.scanStr();
		
		switch(dropCheck) {
		case "예":
			Connection conn = OracleDB.getOracleConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("UPDATE MEMBER SET DROP_YN = 'Y' WHERE MEM_NO = ? AND DROP_YN='N'");
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
	
	public void showUIf(ResultSet rs) throws SQLException {
		while(rs.next()) {
			System.out.print("번호" + " | ");
			System.out.print("아이디"+ " | ");
			System.out.print("이름"+ " | ");
			System.out.print("닉네임"+ " | ");
			System.out.print("성별"+ " | ");
			System.out.print("랭크"+ " | ");
			System.out.print("가입일"+ " | ");
			System.out.println();
			
			int no = rs.getInt("MEM_NO");
			String id = rs.getString("ID");
			String name = rs.getString("UNAME");
			String nick = rs.getString("NICK");
			String gender = rs.getString("GENDER");
			String rank = rs.getString("RANK");
			Timestamp bDate = rs.getTimestamp("BIRTH_DATE");
			
			System.out.print(no + " | ");
			System.out.print(id+ " | ");
			System.out.print(name + " | ");
			System.out.print(nick + " | ");
			System.out.print(gender + " | ");
			System.out.print(rank + " | ");
			System.out.print(bDate + " | ");
			System.out.println();
			
		}
	}
	
}
