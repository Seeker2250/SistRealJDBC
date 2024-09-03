package days02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
public class Ex01_03 {
	//emp에 있는 모든 사원 정보 조회
//	org.doit.domain 패키지
//			ㄴEmpVO.java ( 이름 뒤에 VO 붙으면 value object 라는 뜻)-> 값을 저장하기 위한 object
//			ArrayList<EmpVO> list 
//			이 ArrayList를 출력하는 함수 dispEmp()로 처리
//			
//			comm.util 패키지에 DB연동할 때 사용하도록
/*			 DBConn.java 만들거야
 * 				Connection getConnection() method 구현(이것만 불러오면 다 해결)
 * 				이 Connection 객체를 close 해주는 close() 구현
 * */ 
	public static void main(String[] args) {
		//			1. jdbc driver 로딩 (Class.forName())
		String className = "oracle.jdbc.driver.OracleDriver";//OracleDriver라고 치고 나오는 import문 복사 붙여넣기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ " FROM EMP ";
		int empno;
		String ename;
		String job;
		int mgr;
		LocalDateTime hiredate;// 이게 제일 나을 듯(여러 메소드 지원)
		double sal;
		double comm;
		int deptno;
		//			테이블 attribute에 따라 변수 다 줘
		try {
			Class.forName(className);
			//				2. connection 객체 얻어와 (DriverManager)
			conn = DriverManager.getConnection(url, user, password);
			//				3. 원하는 작업(CRUD)-statement 객체
			stmt = conn.createStatement();
			//				stmt.executeUpdate(sql); 영향 받은 record 갯수를 돌려줘서 return type이 int(insert, update, delete)
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getTimestamp("hiredate").toLocalDateTime();
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				deptno = rs.getInt("deptno");
				System.out.printf("%d\t"
						+ "%s\t"
						+ "%s\t"
						+ "%d\t"
						+ "%tF\t"
						+ "%.2f\t"
						+ "%.2f\t"
						+ "%d\n", empno, ename, job, mgr, hiredate, sal, comm, deptno);//null은 0으로 나와
			}//while
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//					4. connection 객체 닫기 - close()
				rs.close();//닫는 순서 중요
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();



			}
		}}}
