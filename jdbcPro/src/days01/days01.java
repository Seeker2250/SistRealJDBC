package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class days01 {
//	JDBC CLASS 폴더 생성
//	이클립스 실행 설정
//	Java Project생성
//	days01.Ex01.java
	public static void main(String[] args) {
		
		System.out.println("되나");
//		jdbc는 interface(표준 인터페이스)
//		모든 DB를 똑같이 표준화하고 연결할 수 있는 java 표준 interface
//		이걸 구현한 class를 jdbc driver라고 해
		
//		Class.forName()으로 드라이버 로딩
//		DriverManager의 getConnection()을 이용해서 Connection 객체 생성->다리를 놔, connection 작업
//		필요한 작업->(crud)
//		연결 종료 : Connection close()->다리 제거

//		OracleDriver
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url,user,password);
//			crud 작업
			System.out.println(conn);
			if(conn!=null) {
				conn.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
	}
}
