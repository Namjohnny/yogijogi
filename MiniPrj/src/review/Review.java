package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
		}
		
	}
	
public static void showReviewdetail() {
		
		System.out.println("=====리뷰 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		
		
	}
	
	public void updateReview() {
		
		System.out.println("=====리뷰작성=====");
		System.out.print("제목 : ");
		String title = ObjController.scan.nextLine();
		System.out.print("평점 : ");
		String score = ObjController.scan.nextLine();
		System.out.print("내용 : ");
		String content = ObjController.scan.nextLine();
		
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "INSERT INTO REVIEW(TITLE, SCORE, CONTENT)" + "VALUES(?, ?, ?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, score);
			pstmt.setString(3, content);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteReview() {
		
		
		
	}
	
}
