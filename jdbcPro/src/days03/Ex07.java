package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class Ex07 {
	public static void main(String[] args) {


		String sql = "{call UP_SELECTDEPT(?)}"; // 프로시저 호출

		Connection conn = null;
		CallableStatement cstmt = null;

		conn = DBConn.getConnection(); // DB 연결
		ResultSet rs = null;
		try {
			cstmt = conn.prepareCall(sql);
			
			// IN 파라미터 설정
			// OUT 파라미터 설정
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// 프로시저 실행
			cstmt.executeQuery();

			// OUT 파라미터 값 가져오기
			rs = (ResultSet)cstmt.getObject(1);
			int deptno;
			String dname, loc;
			while(rs.next()) {
				deptno = rs.getInt("deptno");
				dname = rs.getString("dname");
				loc = rs.getString("loc");
				  System.out.printf("%d\t%s\t%s\n", deptno, dname, loc);

			}

		
		} catch (SQLException e) {
			// 예외 처리
			e.printStackTrace();
		} finally {
			// 리소스 정리
			try {
				if (cstmt != null) cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		DBConn.close(); // DB 연결 닫기
	}


	//		CREATE OR REPLACE PROCEDURE up_selectdept
	//		(
	//		   pdeptcursor OUT SYS_REFCURSOR
	//		)
	//		IS 
	//
	//		BEGIN
	//		    OPEN pdeptcursor FOR
	//		        SELECT *
	//		        FROM dept;
	//		--EXCEPTION
	//		--  WHEN OTHERS THEN
	//		--    RAISE AP_E)
	//		END;

}
