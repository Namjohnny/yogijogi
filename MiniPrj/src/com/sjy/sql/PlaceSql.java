package com.sjy.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sjy.admin.Place;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class PlaceSql {

	int checknum = 0;
	// db접속
	Connection conn = OracleDB.getOracleConnection();
	PreparedStatement pstmt, pstmt2 = null;
	ResultSet rs = null;

	String sql = "SELECT PNO FROM PLACE";// 번호검사

	String qUpdate;
	String qDelete;



	// 핫플보기
	public void viewPlace(String place) {
		System.out.println(place);
//		String qSelect = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 FROM PLACE p "
//				+ "JOIN PLACETYPE pt ON p.PTNO = pt.PTNO "
//				+ "WHERE  p.DELETE_YN = 'N'"
//				+ "AND pt.PTYPE1 = '음식점'"
//				+ " order by pno" ;
		String qSelect = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 "
				+ " FROM PLACE p "
				+ "JOIN PLACETYPE pt "
				+ "ON p.PTNO = pt.PTNO "
				+ "WHERE  p.DELETE_YN = 'N'"
				+ "and pt.PTYPE1 = '"+ place +"'"
				+ "order by pno";		
		try {
			pstmt = conn.prepareStatement(qSelect);
			ResultSet rs = pstmt.executeQuery();

			System.out.print("번호" + " | ");
			System.out.print("이름" + " | ");
			System.out.print("지역" + " | ");
			System.out.println();
			while (rs.next()) {
				Place.dto.setPno(rs.getInt("pno"));
				Place.dto.setPname(rs.getString("PNAME"));
				Place.dto.setLoca(rs.getString("LOCA"));

				System.out.print(Place.dto.getPno() + " | ");
				System.out.print(Place.dto.getPname() + " | ");
				System.out.print(Place.dto.getLoca() + " | ");
				System.out.println();

			}
			detailtype();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}

	}

	// 세부 정보 확인
	public void detailtype() {
		System.out.print("세부정보확인: ");
		int num = ObjController.scanInt();
		String qSelect2 = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 " + "FROM PLACE p "
				+ "JOIN PLACETYPE pt ON p.PTNO = pt.PTNO " + "WHERE  p.DELETE_YN = 'N' AND p.PNO = '" + num + "'";

		checknum = new numCheck().pnumCheck(num);
		if (checknum == 1) {
			try {
				pstmt = conn.prepareStatement(qSelect2);
				ResultSet rs = pstmt.executeQuery();

				System.out.print("번호" + "|");
				System.out.print("대분류" + "|");
				System.out.print("소분류" + "|");

				while (rs.next()) {
					Place.dto.setPtno(rs.getInt("PTNO"));
					;
					Place.dto.setPtype1(rs.getString("PTYPE1"));
					Place.dto.setPtype2(rs.getString("PTYPE2"));
					System.out.println();

					System.out.print(Place.dto.getPtno() + " | ");
					System.out.print(Place.dto.getPtype1() + " | ");
					System.out.print(Place.dto.getPtype2() + " | ");
					System.out.println();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
		} else {
			System.out.println("※없는 번호 입니다...※");
			detailtype();
		}
	}

	// insert1
	public void addPlace1() {

		try {
			String qInsert1 = "INSERT INTO PLACE(PNO, PNAME, LOCA, PTNO)"
					+ " VALUES(PLACE_NO_SEQ.NEXTVAL,?,?,PLACE_NO_SEQ.NEXTVAL)";
			System.out.println("장소등록");
			System.out.print("장소명: ");
			String pname = ObjController.scanStr();

			System.out.print("지역: ");
			String plocal = ObjController.scanStr();

			pstmt = conn.prepareStatement(qInsert1);
			pstmt.setString(1, pname);
			pstmt.setString(2, plocal);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("---------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql에러");
		} finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}

	}

	// insert2
	public void addPlace2() {
		String qInsert2 = "INSERT INTO PLACETYPE(PTNO, PTYPE1, PTYPE2)" + " VALUES(PLACETYPE_NO_SEQ.NEXTVAL, ?,?)";
		System.out.println("세부지역입력");
		System.out.print("세부지역1: ");
		String p1 = ObjController.scanStr();

		System.out.print("세부지역2: ");
		String p2 = ObjController.scanStr();

		try {
			pstmt = conn.prepareStatement(qInsert2);
			pstmt.setString(1, p1);
			pstmt.setString(2, p2);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("추가완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(pstmt2);
			OracleDB.close(rs);
		}

	}

	public void modPlace() {
		System.out.print("수정할 장소 번호 입력:");
		int updPlace = ObjController.scanInt();
		String pname, ploca, ptype1, ptype2;

		String getdata = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 " + "FROM PLACE p " + "JOIN PLACETYPE pt"
				+ " ON p.PTNO = pt.PTNO " + "WHERE  p.DELETE_YN = 'N'" + "AND PNO = ? order by pno";

		String qUpdate1 = "UPDATE PLACE SET PNAME= ?, LOCA =? WHERE PNO = ? order by pno";
		String qUpdate2 = "UPDATE PLACETYPE SET PTYPE1 = ?, PTYPE2 =? WHERE PTNO = ? order by pno";

		checknum = new numCheck().pnumCheck(updPlace);
		// 번호가 db에 있는 지없는지 판별
		int result1, result2 = 0;
		if (checknum == 1) {
			try {
				// select로 번호에 맞는 정보 가져오기
				System.out.println("선택한 핫플정보");

				pstmt = conn.prepareStatement(getdata);
				pstmt.setInt(1, updPlace);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					System.out.print("장소이름"+ " | ");
					System.out.print("장소지역"+ " | ");
					System.out.print("세부지역1"+ " | ");
					System.out.print("세부지역2"+ "\n");
					System.out.println("-----------------------------------------");
					System.out.print(rs.getString("PNAME") + " | ");
					System.out.print(rs.getString("LOCA")+ " | ");
					System.out.print(rs.getString("PTYPE1")+ " | ");
					System.out.print(rs.getString("PTYPE2")+ "\n");
				}
					
					System.out.println("----------정보입력----------");
					
					System.out.print("핫플이름:");
					pname = ObjController.scanStr();
					System.out.print("핫플지역");
					ploca = ObjController.scanStr();
					System.out.print("세부지역1");
					ptype1 = ObjController.scanStr();
					System.out.print("세부지역2");
					ptype2 = ObjController.scanStr();
					
					PreparedStatement pstmt1 = conn.prepareStatement(qUpdate1);
					PreparedStatement pstmt2 = conn.prepareStatement(qUpdate2);

					pstmt1.setString(1,pname);
					pstmt1.setString(2,ploca);
					pstmt1.setInt(3, updPlace);
					
					pstmt2.setString(1,ptype1);
					pstmt2.setString(2,ptype2);
					pstmt2.setInt(3, updPlace);
					result1 = pstmt1.executeUpdate();
					result2 = pstmt2.executeUpdate();
					
					if (result1 == 1 && result2 == 1) {
						System.out.println(updPlace + "번이 수정되었습니다.");
					} else {
						System.out.println("실패");
					}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
		} else {
			System.out.println("번호에 맞는 장소가 없습니다.");
		}

	}// modPlace()

	public void delPlace() {
		System.out.print("삭제할 장소 번호 입력:");
		int delPlace = ObjController.scanInt();
		// 번호가 db에 있는 지없는지 판별

		// db연동
		String sql = "UPDATE PLACE SET DELETE_YN='Y' WHERE PNO = ?";

		checknum = new numCheck().pnumCheck(delPlace);
		
		if (checknum == 1) {
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, delPlace);
				int result = pstmt.executeUpdate();
				
				if (result == 1) {
					System.out.println(delPlace + "번 장소 삭제완료");
				} else {
					System.out.println("실패");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
		} else {
			System.out.println("※없는 번호 입니다...※");
			delPlace();
		}
	}

}// class
