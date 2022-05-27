package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.spi.DirStateFactory.Result;

import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class Review {

public static void showReviewList() {
		
		System.out.println("=====리뷰 목록 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql ="SELECT * FROM REVIEW WHERE R_NO ORDER BY REGISTER_DATE DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.print("글번호");
			System.out.print("|");
			System.out.print("회원번호");
			System.out.print("|");
			System.out.print("핫플번호");
			System.out.print("|");
			System.out.print("리뷰제목");
			System.out.print("|");
			System.out.print("평점");
			System.out.print("|");
			System.out.print("리뷰작성일");
			System.out.print("\n============");
			
			while(rs.next()) {
				int rno = rs.getInt("RNO");
				int mno = rs.getInt("MNO");
				int prno = rs.getInt("PNO");
				String title = rs.getString("TITLE");
				String score = rs.getString("SCORE");
				Timestamp rdate = rs.getTimestamp("RDATE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
		showReviewDetail();
		
	}
	
public static void showReviewDetail() {
		
		System.out.println("=====리뷰 상세 보기=====");
		System.out.println("조회할 게시글 번호 입력 : ");
		int no = ObjController.scanInt();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SEELECT* FROM REVIEW WHERE NO = ? AND DELETE_YN = 'N'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery(sql);
			
			if(rs.next()) {
				String title = (rs).getString("TITLE");
				String content = (rs).getString("CONTENT");
				
				System.out.println(title);
				System.out.println(content);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	public void updateReview() {
		
		//작성자 로그인한 유저 확인
		if(User.LoginUserNo == 0) {
			System.out.println("로그인 한 유저만 글을 쓸 수 있습니다.");
			return;
		}
		
		System.out.println("=====리뷰작성=====");
		System.out.print("제목 : ");
		String title = ObjController.scanStr();
		System.out.print("평점 : ");
		String score = ObjController.scanStr();
		System.out.print("내용 : ");
		String content = ObjController.scanStr();
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO REVIEW(TITLE, PNO, SCORE, REVIEW)" + "VALUES(?, ?, ?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, score);
			pstmt.setString(3, content);
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("리뷰 등록 성공");
			}else {
				System.out.println("리뷰 등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	         OracleDB.close(conn);
	         OracleDB.close(pstmt);
	      }
		
	}
	
	public void deleteReview() {
		
		
		
	}
	
}
