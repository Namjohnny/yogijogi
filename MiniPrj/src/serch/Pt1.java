package serch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

	public class Pt1 {

		public static void serch1() {
		
		System.out.println("=====한식 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO <= 3";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
		public static void serch2() {
		
		System.out.println("=====일식 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 4 AND 6";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}

	public static void serch3() {
	
	 System.out.println("=====중식 정보 상세 보기=====");
	
	 Connection conn = OracleDB.getOracleConnection();
	
	String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 7 AND 9";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int pno = rs.getInt("PNO");
		    String pname = rs.getString("PNAME");
			String loca = rs.getString("LOCA");
				
			System.out.print(pno);
			System.out.print("|");
			System.out.print(pname);
			System.out.print("|");
			System.out.print(loca);
			System.out.println();
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		OracleDB.close(conn);
		OracleDB.close(rs);
		OracleDB.close(pstmt);
	}
	
}

	public static void serch4() {
	
	System.out.println("=====양식 정보 상세 보기=====");
	
	Connection conn = OracleDB.getOracleConnection();
	
	String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 10 AND 12";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int pno = rs.getInt("PNO");
		    String pname = rs.getString("PNAME");
			String loca = rs.getString("LOCA");
				
			System.out.print(pno);
			System.out.print("|");
			System.out.print(pname);
			System.out.print("|");
			System.out.print(loca);
			System.out.println();
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		OracleDB.close(conn);
		OracleDB.close(rs);
		OracleDB.close(pstmt);
	}
	
}

	public static void serch5() {
	
	System.out.println("=====기타 정보 상세 보기=====");
	
	Connection conn = OracleDB.getOracleConnection();
	
	String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 13 AND 15";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int pno = rs.getInt("PNO");
		    String pname = rs.getString("PNAME");
			String loca = rs.getString("LOCA");
				
			System.out.print(pno);
			System.out.print("|");
			System.out.print(pname);
			System.out.print("|");
			System.out.print(loca);
			System.out.println();
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		OracleDB.close(conn);
		OracleDB.close(rs);
		OracleDB.close(pstmt);
	}
	
}

	public static void serch6() {
	
	System.out.println("=====박물관 정보 상세 보기=====");
	
	Connection conn = OracleDB.getOracleConnection();
	
	String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 16 AND 18";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int pno = rs.getInt("PNO");
		    String pname = rs.getString("PNAME");
			String loca = rs.getString("LOCA");
				
			System.out.print(pno);
			System.out.print("|");
			System.out.print(pname);
			System.out.print("|");
			System.out.print(loca);
			System.out.println();
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		OracleDB.close(conn);
		OracleDB.close(rs);
		OracleDB.close(pstmt);
	}
	
}

	public static void serch7() {
	
	System.out.println("=====미술관 정보 상세 보기=====");
	
	Connection conn = OracleDB.getOracleConnection();
	
	String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 19 AND 20";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int pno = rs.getInt("PNO");
		    String pname = rs.getString("PNAME");
			String loca = rs.getString("LOCA");
				
			System.out.print(pno);
			System.out.print("|");
			System.out.print(pname);
			System.out.print("|");
			System.out.print(loca);
			System.out.println();
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		OracleDB.close(conn);
		OracleDB.close(rs);
		OracleDB.close(pstmt);
	}
	
}
	
	public static void serch8() {
		
		System.out.println("=====영화관 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 21 AND 22";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	public static void serch9() {
		
		System.out.println("=====극장 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 23 AND 24";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	public static void serch10() {
		
		System.out.println("=====기타 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 25 AND 26";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	public static void serch11() {
		
		System.out.println("=====주점 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 27 AND 30";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	public static void serch12() {
		
		System.out.println("=====클럽 정보 상세 보기=====");
		
		Connection conn = OracleDB.getOracleConnection();
		
		String sql = "SELECT * FROM PLACE WHERE PNO BETWEEN 31 AND 32";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("PNO");
			    String pname = rs.getString("PNAME");
				String loca = rs.getString("LOCA");
					
				System.out.print(pno);
				System.out.print("|");
				System.out.print(pname);
				System.out.print("|");
				System.out.print(loca);
				System.out.println();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleDB.close(conn);
			OracleDB.close(rs);
			OracleDB.close(pstmt);
		}
		
	}
	
	
}//class
