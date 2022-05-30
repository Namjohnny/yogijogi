package com.sjy.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.phs.payment.Pay;
import com.sjy.admin.PlaceManage;
import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class RsvSql {
//	Connection conn = OracleDB.getOracleConnection();
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
	
	
	public void RsvView() {
		RsvAll();
	}

	// 장소 세부정보 확인
	public void dtailPlace() {
	}

	// 예약등록
	public void addRsv() {
		//int usernum = 3;// new User().getNo(); //사용자 번호 받아오기
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Date today = new Date();
		
		System.out.println("-------예약자-------");
		System.out.print("사용자 번호: ");
		int usernum = ObjController.scanInt();
		
		int chun =  new UserSql().numCheck(usernum);
		if (chun == -1) {System.out.println("등록되지않은 사용자입니다.");return;}
		
		System.out.println("-------장소-------");
		new PlaceManage().view();
		System.out.print("예약장소 번호: ");
		int rsvPno = ObjController.scanInt();
		int chpn = new numCheck().pnumCheck(rsvPno);
		if (chpn ==-1) {System.out.println("등록되지않은 장소입니다.");return;}
		
		
		System.out.println("-------오늘날짜-------");
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		System.out.println("Date: " + date.format(today));
		System.out.println("Time: " + time.format(today));

		System.out.print("예약 날짜 및 시간(24h) (yy-mm-dd hh:mi) : ");
		String rsvDate = ObjController.scanStr();

		
		String sql = "INSERT INTO RESERVATION(RSV_NO, MEM_NO, P_NO, RSV_DATE, CANCEL)"
				+ "VALUES(RESERVATION_NO_SEQ.NEXTVAL, ?, ?, to_date(?, 'yyyy/mm/dd hh24:mi'), 'N')";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, usernum);
			pstmt.setInt(2, rsvPno);
			pstmt.setString(3, rsvDate);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("예약금 20,000원을 결제합니다.");
				Pay.payNow02();
			} else {
				System.out.println("예약 실패..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
	}

	// 예약 수정
	public void modRsv() {
		//전체 예약 내용보여주기 
		RsvAll();
		System.out.print("조회할 예약 번호 입력 : ");
		int modnum = ObjController.scanInt();
		//선택한 예약내용
		modChnum(modnum);
		
		//예약내용 변경
		modUpd(modnum);
	}

	// 예약 취소
	public void delRsv() {
		// System.out.println("예약을 취소하시겠습니까? (y/n) : ");
		// String cancle = ObjController.scanStr();
		System.out.print("번호: ");
		int clnum = ObjController.scanInt();

		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "UPDATE RESERVATION SET CANCEL = 'Y' WHERE RSV_NO = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, clnum);
			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println(clnum + "번 예약 취소");
			} else {
				System.out.println("해당 번호는없는 번호입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}

	}
	
	//선택한 번호(modnum) 예약내용
	public void modChnum(int modnum ) {
		
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM RSV_VIEW WHERE RSV_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, modnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					int rsvNo = rs.getInt("RSV_NO");
					Timestamp rsvDate = rs.getTimestamp("RSV_DATE");
					String pName = rs.getString("P_NAME");
					String loca = rs.getString("LOCA");
					int payNo = rs.getInt("PAY_NO");
					String pthName = rs.getString("PTH_NAME");
					
					SimpleDateFormat date = new SimpleDateFormat("yy-MM-dd | HH:mm");
					//System.out.println("Date: " + date.format(rsvDate));
					
					System.out.println("예약 번호 : " + rsvNo);
					System.out.println("예약일 : " +date.format(rsvDate));
					System.out.println("상호명 : " + pName);
					System.out.println("가게 주소 : " + loca);
					System.out.println("결제 번호 : " + payNo);
					System.out.println("결제 수단 : " + pthName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
	}
	
	//수정할내용 입력후 UPDATE
	public void modUpd(int modnum) {
		System.out.println("===== 예약 변경 =====");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		//변경 날짜 입력 받기
		System.out.println("yy-mm-dd hh:mm 으로 입력해주세요 ");
		System.out.print("변경 원하는 날짜와 시간 : ");
		String rsvDate = ObjController.scanStr();
		
		Connection conn = OracleDB.getOracleConnection();
		//데이터 수정  to_date(?, 'yyyy/mm/dd hh24:mi')
		String sql = "UPDATE RESERVATION SET RSV_DATE = TO_DATE(?,'yyyy/mm/dd hh24:mi') WHERE RSV_NO = ?";
		
		try {
//			String sql1 = "SELECT * FROM RSV_VIEW";
//			pstmt = conn.prepareStatement(sql1);
//			rs = pstmt.executeQuery();
//			rs.next();
//			int rsvNo = rs.getInt("RSV_NO");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rsvDate);
			pstmt.setInt(2, modnum);
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// 변경된 뷰 출력
		String view = "SELECT * FROM RSV_VIEW";
		try {
			pstmt = conn.prepareStatement(view);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int rsvNo2 = rs.getInt("RSV_NO");
				Timestamp rsvDate2 = rs.getTimestamp("RSV_DATE");
				String pName = rs.getString("P_NAME");
				String loca = rs.getString("LOCA");
				int payNo = rs.getInt("PAY_NO");
				String pthName = rs.getString("PTH_NAME");
				
				SimpleDateFormat date = new SimpleDateFormat("yy-MM-dd | HH:mm");
				
				System.out.println("==== 변경된 내역 ====");
				System.out.println("예약 번호 : " + rsvNo2);
				System.out.println("예약일 : " + date.format(rsvDate2));
				System.out.println("상호명 : " + pName);
				System.out.println("가게 주소 : " + loca);
				System.out.println("결제 번호 : " + payNo);
				System.out.println("결제 수단 : " + pthName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}

		// 성공적으로 변경됐습니다. 출력
		System.out.println("예약을 성공적으로 변경하였습니다.");
	}
	
	
	public void RsvAll() {
		String sql = "SELECT * FROM RSV_LIST WHERE CANCEL = 'N' ORDER BY RSV_DATE DESC";
		String sql2 = "SELECT MEM_NO, NICK FROM MEMBER WHERE MEM_NO =?";
		Connection conn = OracleDB.getOracleConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);

			rs = pstmt.executeQuery();

			System.out.print("예약번호" + " | ");
			System.out.print("예약자" + " | ");
			System.out.print("예약장소" + " | ");
			System.out.print("예약일" + " | ");

			System.out.println("\n--------------------------------");
			while (rs.next()) {
				int num = rs.getInt("MEM_NO");
				pstmt2.setInt(1, num);

				rs2 = pstmt2.executeQuery();

				if (rs2.next()) {
					int rsvNo = rs.getInt("RSV_NO");
					String mName = rs2.getString("NICK");
					String pName = rs.getString("PNAME");
					String rsvDate = rs.getString("RSV_DATE");

					System.out.print(rsvNo + " | ");
					System.out.print(mName + " | ");
					System.out.print(pName + " | ");
					System.out.print(rsvDate + " | ");
					System.out.println();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);}
		System.out.println();
		System.out.println("--------------------------------");
	}

}// class
