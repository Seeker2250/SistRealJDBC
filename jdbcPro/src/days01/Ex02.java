package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex02 {
public static void main(String[] args) {
		

		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		Statement stmt = null;//��ޱ��
		ResultSet rs = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url,user,password);
//			crud �۾�
			String sql = "SELECT *"
					+ " FROM dept ";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);//select문은 executeQuery로 실행
//			 insert update delete문은 executeUpdate로 실행
			int deptno;
			String dname, loc;
			while( rs.next() ) {//다음 데이터가 없을 때까지
				deptno = rs.getInt("deptno");
				dname = rs.getString("dname");
				loc = rs.getString("loc");
				System.out.printf("%d\t%s\t%s\n", deptno, dname, loc);
			}
			rs.close();
			stmt.close();//��� ��
//			System.out.println(conn);
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
