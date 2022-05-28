package com.sjy.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.sjy.admin.Place;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class PlaceSql {

	int checknum = 0;
	// db접속
	Connection conn = OracleDB.getOracleConnection();
	PreparedStatement pstmt, pstmt2 = null;
	ResultSet rs = null;
	Place p = new Place();

	String qUpdate;
	String qDelete;

	// ----------------------------------------------------------------------------------------------------------------------------
	// 핫플보기
	
	//모든장소 보여주기
	public ArrayList<Place> showListAll() {
		ArrayList<Place> dtos = new ArrayList<Place>();
		String sql = "SELECT PNO,PNAME, LOCA, P.PTNO, PT.PTYPE1, PT.PTYPE2 FROM PLACE P JOIN PLACETYPE PT ON P.PTNO = PT.PTNO order by pno";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				int pNo = rs.getInt("PNO");
				String pName = rs.getString("PNAME");
				String pLoca = rs.getString("LOCA");
				
				int ptNo = rs.getInt("PTNO");
				String ptType1 = rs.getString("PTYPE1");
				String ptType2 = rs.getString("PTYPE2");
				
				Place p = new Place(pNo, pName, pLoca, ptNo, ptType1, ptType2);
				dtos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dtos;
	}
	//구별로 장소 보여주기
	public ArrayList<Place> showListlc(String lc) {
		ArrayList<Place> dtos = new ArrayList<Place>();
		String sql = "SELECT PNO,PNAME, LOCA, P.PTNO, PT.PTYPE1, PT.PTYPE2 FROM PLACE P JOIN PLACETYPE PT ON P.PTNO = PT.PTNO "
				+ "where loca like'서울 "+lc+ "%'"
				+ "order by pno";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				int pNo = rs.getInt("PNO");
				String pName = rs.getString("PNAME");
				String pLoca = rs.getString("LOCA");
				
				int ptNo = rs.getInt("PTNO");
				String ptType1 = rs.getString("PTYPE1");
				String ptType2 = rs.getString("PTYPE2");
				
				Place p = new Place(pNo, pName, pLoca, ptNo, ptType1, ptType2);
				dtos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dtos;
	}
		
		
		
	public void showList(String place) {
		System.out.println(place);
//		String qSelect = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 FROM PLACE p "
//				+ "JOIN PLACETYPE pt ON p.PTNO = pt.PTNO "
//				+ "WHERE  p.DELETE_YN = 'N'"
//				+ "AND pt.PTYPE1 = '음식점'"
//				+ " order by pno" ;
		String qSelect = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 " 
				+ " FROM PLACE p " + "JOIN PLACETYPE pt "
				+ "ON p.PTNO = pt.PTNO " 
				+ "WHERE  p.DELETE_YN = 'N'" 
				+ "and pt.PTYPE1 = ?" 
				+ "order by pno";
		try {
			pstmt = conn.prepareStatement(qSelect);
			pstmt.setString(1, place);
			ResultSet rs = pstmt.executeQuery();

			System.out.print("번호" + " | ");
			System.out.print("이름" + " | ");
			System.out.print("지역" + " | ");
			System.out.println();
			while (rs.next()) {
				p.setPno(rs.getInt("PNO"));
				p.setPname(rs.getString("PNAME"));
				p.setLoca(rs.getString("LOCA"));

				System.out.print(p.getPno() + " | ");
				System.out.print(p.getPname() + " | ");
				System.out.print(p.getLoca() + " | ");
				System.out.println();

			}
			detailtype(place);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println();
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}

	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// 세부 정보 확인
	public void detailtype(String place) {
		Place p = new Place();
		System.out.println(place);
		System.out.print("세부정보확인: ");
		int num = ObjController.scanInt();

		String qSelect2 = "SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 " + "FROM PLACETYPE pt " + "JOIN PLACE p "
				+ "ON p.PTNO = pt.PTNO " + "WHERE  p.DELETE_YN = 'N' " + "AND p.PNO = ?" + "AND pt.PTYPE1 = ?";

		checknum = new numCheck().pnumCheck(num);
		if (checknum == 1) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(qSelect2);
				pstmt.setInt(1, num);
				pstmt.setString(2, place);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					System.out.print("번호" + "|");
					System.out.print("대분류" + "|");
					System.out.print("소분류" + "|");

					Place.dto.setPtno(rs.getInt("PTNO"));
					Place.dto.setPtype1(rs.getString("PTYPE1"));
					Place.dto.setPtype2(rs.getString("PTYPE2"));
					System.out.println();

					System.out.print(Place.dto.getPtno() + " | ");
					System.out.print(Place.dto.getPtype1() + " | ");
					System.out.print(Place.dto.getPtype2() + " | ");
					System.out.println();
					return;
				}
				System.out.println("※해당 분류에는 없는 번호 입니다...※");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
		} else {
			System.out.println("※없는 번호 입니다...※");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// insert food
	public void addFood(String place) {
		// String qInsert2 = "INSERT INTO PLACETYPE(PTNO, PTYPE1, PTYPE2)" + " VALUES(?,
		// ?,?)";
		System.out.print(place + " 소분류: ");
		gPtye2(place);

		System.out.println("세부지역입력");
		System.out.println("대분류: " + place);

		System.out.print("소분류: ");
		String p2 = ObjController.scanStr();
		int fnum = 0;
		fnum = fType1( p2);
		insertPlace(fnum);

		return;

	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// insert culture
	public void addCulture(String place) {
		System.out.print(place + " 소분류: ");
		gPtye2(place);

		System.out.println("세부지역입력");
		System.out.println("대분류: " + place);

		System.out.print("소분류: ");
		String p2 = ObjController.scanStr();
		int culNum = 0;

		culNum = cType1( p2);

		insertPlace(culNum);

		return;

	}
	
	// ----------------------------------------------------------------------------------------------------------------------------
	// insert 유흥
	public void addP(String place) {
		System.out.print(place + " 소분류: ");
		gPtye2(place);

		System.out.println("세부지역입력");
		System.out.println("대분류: " + place);

		System.out.print("소분류: ");
		String p2 = ObjController.scanStr();
		int pNum = 0;
		pNum = pType1( p2);
		insertPlace(pNum);

	}
	
	//장소삽입 공통부분
	public void insertPlace(int placeNum) {
		try {
			System.out.println("--장소|지역--");

			String qInsert1 = "INSERT INTO PLACE(PNO, PNAME, LOCA, PTNO)" 
			+ " VALUES(PLACE_PTNO_SEQ.NEXTVAL,?,?,?)";
			System.out.println("지역명은 지역명 구명 순으로 입력해주세요");
			System.out.println("장소등록");
			System.out.print("장소명: ");
			String pname = ObjController.scanStr();

			System.out.print("지역: ");
			String plocal = ObjController.scanStr();

			pstmt = conn.prepareStatement(qInsert1);
			pstmt.setString(1, pname);
			pstmt.setString(2, plocal);
			pstmt.setInt(3, placeNum);
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("---------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(pstmt2);
			OracleDB.close(rs);
		}
		return;
	}
	
	//--------------------------------------------------------------------------------------------------------
	//update
	public void modPlace(String place) {
		System.out.print("수정할 장소 번호 입력:");
		int updPlace = ObjController.scanInt();
		String pname, ploca, ptype2;

		String getdata = " SELECT PNO, PNAME, LOCA, pt.PTNO, PTYPE1, PTYPE2 " 
				+ "FROM PLACE p " + "JOIN PLACETYPE pt"
				+ " ON p.PTNO = pt.PTNO " 
				+ "WHERE  p.DELETE_YN = 'N'" 
				+ "AND PNO = ? AND PTYPE1 = ?"
				+ "order by pno";

		String qUpdate1 = "UPDATE PLACE SET PNAME= ?, PTNO =?, LOCA =? WHERE PNO = ?";
		//String qUpdate2 = "UPDATE PLACETYPE SET PTYPE1 = ?, PTYPE2 =? WHERE PTNO = ? order by pno";

		checknum = new numCheck().pnumCheck(updPlace);
		// 번호가 db에 있는 지없는지 판별
		
		
		
		if (checknum == 1) {
			try {
				// select로 번호에 맞는 정보 가져오기

				pstmt = conn.prepareStatement(getdata);
				pstmt.setInt(1, updPlace);
				pstmt.setString(2, place);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					System.out.println("선택한 핫플정보");
					System.out.print("장소이름" + " | ");
					System.out.print("장소지역" + " | ");
					System.out.print("세부지역1" + " | ");
					System.out.print("세부지역2" + "\n");
					System.out.println("-----------------------------------------");
					System.out.print(rs.getString("PNAME") + " | ");
					System.out.print(rs.getString("LOCA") + " | ");
					System.out.print(rs.getString("PTYPE1") + " | ");
					System.out.print(rs.getString("PTYPE2") + "\n");
				}else {System.out.println("해당번호는 분류에 없는 번호입니다\n");return;}
				
				
				System.out.println("----------정보입력----------");
				System.out.println("대분류: " + place);
				System.out.print("소분류: ");
				ptype2 = ObjController.scanStr();

				int num = 0 ;
				if ("음식점".equals(place)) {
					num = fType1(ptype2);
				}else if ("문화체험".equals(place)) {
					num =cType1(ptype2);
				}else if ("유흥".equals(place)) {
					num =pType1(ptype2);
				}else {
					System.out.println(num);
					System.out.println("소분류 입력오류"); return;}
				
				System.out.println(num);
				System.out.print("핫플이름:");
				pname = ObjController.scanStr();
				System.out.print("핫플지역");
				ploca = ObjController.scanStr();

				PreparedStatement pstmt1 = conn.prepareStatement(qUpdate1);

				pstmt1.setString(1, pname);
				pstmt1.setInt(2, num);
				pstmt1.setString(3, ploca);
				pstmt1.setInt(4, updPlace);

				int result1 = pstmt1.executeUpdate();

				if (result1 == 1 ) {
					System.out.println(updPlace + "번이 수정되었습니다.");
					System.out.println();
				} else {
					System.out.println("실패");
				}

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				OracleDB.close(conn);
				OracleDB.close(pstmt);
				OracleDB.close(rs);
			}
		} else {
			System.out.println("번호에 맞는 장소가 없습니다.\n");
		}

	}// modPlace()

	public void delPlace(String place) {
		System.out.print("삭제할 장소 번호 입력:");
		int delPlace = ObjController.scanInt();

		// 입력받은 번호의 ptno / ptype2가져오기
		String getno = "SELECT P.PNO, PTYPE2, PT.PTNO FROM PLACE P JOIN PLACETYPE PT ON P.PTNO = PT.PTNO WHERE PNO =?";
		PreparedStatement pstmtg;
		if (delPlace>=1) {
			System.out.println(delPlace);
		
		try {
			pstmtg = conn.prepareStatement(getno);
			pstmtg.setInt(1, delPlace);
			ResultSet rs = pstmtg.executeQuery();

			if (rs.next()) {
				String gpt2 = rs.getString("PTYPE2");
				int gpn = rs.getInt("PTNO");
				
				int num = 0;
				System.out.println(gpn);
				
				if (gpn >=0 && gpn<=5&&"음식점".equals(place)) {
					num = fType1(gpt2);
				} else if (gpn >=6 && gpn<=10&&"문화체험".equals(place)) {
					num = cType1(gpt2);
				} else if (gpn >=11 && gpn<=12&&"유흥".equals(place)) {
					num = pType1(gpt2);
				} else {
					System.out.println("번호를 다시입력해주세요");
					System.out.println("현재 화면에 맞는 분류가 아닙니다.");
					return;
				}
				
				
				String sql = "UPDATE PLACE SET DELETE_YN='Y' WHERE PNO = ? AND PTNO = ? ";

				// 번호가 db에 있는 지없는지 판별
				checknum = new numCheck().pnumCheck(delPlace);

				if (checknum == 1) {
					try {

						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, delPlace);
						pstmt.setInt(2, num);
						int result = pstmt.executeUpdate();

						if (result == 1) {
							System.out.println(delPlace + "번 장소 삭제완료");
						} else {
							System.out.println("실패");
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				} 
			}else {
				System.out.println("※없는 번호 입니다...※");
			}
			}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		}else{System.out.println("1이상의 자연수를 입력해주세요");}
}// delPlace()
			 
		

				
	
	
		
		
	

	// 세부지역 가져오기
	public String gPtye2(String place) {

		String sql = "SELECT PTYPE2, PTYPE1 FROM PLACETYPE WHERE PTYPE1= ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				p.setPtype2(rs.getString("PTYPE2"));
				System.out.print(p.getPtype2() + "/");
			}
			System.out.println();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "실패";
	}

	//--------------------------------------------------------------------------------
	//분류
	public int fType1( String p2){
		int fNum;
		if ("한식".equals(p2)) {
			fNum = 1;
		} else if ("일식".equals(p2)) {
			fNum = 2;
		} else if ("중식".equals(p2)) {
			fNum = 3;
		} else if ("양식".equals(p2)) {
			fNum = 4;
		} else if ("기타".equals(p2)) {
			fNum = 5;
		} else {
			System.out.println("소분류를 다시입력해주세요");
			return fType1(p2);
		}
		return fNum;
	}
	
	public int cType1(String p2){
		int cNum;
		if ("박물관".equals(p2)) {
			cNum = 6;
		} else if ("미술관".equals(p2)) {
			cNum = 7;
		} else if ("영화관".equals(p2)) {
			cNum = 8;
		} else if ("극장".equals(p2)) {
			cNum = 9;
		} else if ("기타".equals(p2)) {
			cNum = 10;
		} else {
			System.out.println("소분류를 다시입력해주세요");
			return cType1(p2);
		}
		return cNum;
	}
	
	public int pType1( String p2){
		int pNum;
		if ("주점".equals(p2)) {
			pNum = 11;
		} else if ("클럽".equals(p2)) {
			pNum = 12;
		} else {
			System.out.println("소분류를 다시입력해주세요");
			return pType1( p2);
		}
		return pNum;
	}
	
	

}// class
