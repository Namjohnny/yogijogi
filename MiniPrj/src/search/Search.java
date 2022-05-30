package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.OracleDB;

public class Search {

	public static void showList() {
		
		System.out.println("=====목록=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACETYPE ORDER BY PTNO";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.print("종류별 장소 번호");
			System.out.print("|");
			System.out.print("종류별 장소");
			System.out.print("|");
			System.out.println("종류");
			
			while(rs.next()) {
				int ptno = rs.getInt("PTNO");
				String ptype1 = rs.getString("PTYPE1");
				String ptype2 = rs.getString("PTYPE2");
				
				System.out.print(ptno);
				System.out.print("|");
				System.out.print(ptype1);
				System.out.print("|");
				System.out.print(ptype2);
				System.out.println("");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
}
