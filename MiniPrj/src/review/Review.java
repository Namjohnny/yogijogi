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
		
		String sql ="SELECT * FROM REVIEW WHERE DELETE_YN = 'N' ORDER BY R_NO ASC";
		
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
			System.out.println("\n============");
			
			while(rs.next()) {
				int rno = rs.getInt("R_NO");
				int mno = rs.getInt("MEM_NO");
				int prno = rs.getInt("PNO");
				String title = rs.getString("TITLE");
				String score = rs.getString("SCORE");
				Timestamp rdate = rs.getTimestamp("WDATE");
				
				System.out.print(rno);
				System.out.print("|");
				System.out.print(mno);
				System.out.print("|");
				System.out.print(prno);
				System.out.print("|");
				System.out.print(title);
				System.out.print("|");
				System.out.print(score);
				System.out.print("|");
				System.out.print(rdate);
				System.out.println();
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
		System.out.print("조회할 게시글 번호 입력 : ");
		int no = ObjController.scanInt();
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SEELECT * FROM REVIEW WHERE R_NO = ? AND DELETE_YN = 'N'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("TITLE");
				String score = rs.getString("SCORE");
				String review = rs.getString("REVIEW");
				
				System.out.println(title);
				System.out.println(score);
				System.out.println(review);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	public static void updateReview() {
		
		if(User.LoginUserNo == 0) {
			System.out.println("로그인 한 유저만 글을 쓸 수 있습니다.");
			return;
		}
		
		System.out.println("=====리뷰작성=====");
		System.out.println("핫플 번호");
		int pno = ObjController.scanInt();
		System.out.print("제목 : ");
		String title = ObjController.scanStr();
		System.out.print("평점 : ");
		String score = ObjController.scanStr();
		System.out.print("내용 : ");
		String review = ObjController.scanStr();
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO REVIEW(R_NO, MEM_NO, PNO, TITLE, SCORE, REVIEW, WDATE, DELETE_YN)" 
		+ "VALUES(REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 'N')";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, User.LoginUserNo);
			pstmt.setInt(2, pno);
			pstmt.setString(3, title);
			pstmt.setString(4, score);
			pstmt.setString(5, review);
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
	
}
