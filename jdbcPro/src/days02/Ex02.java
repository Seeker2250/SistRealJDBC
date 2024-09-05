package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.doit.domain.DeptVO;
import org.doit.domain.EmpVO;

import com.util.DBConn;

/*
 * org.doit.domain.DeptVO.java
 * 			1. 실제 SELECT * FROM dept 쿼리를 실행해서 부서 정보를 가지고 있는 ArrayList<DeptVO> deptList를 만들거야
 * 			2. 부서정보 출력
 * 			3. 부서 번호를 선택하세요->선택번호 입력
 * 			4. SELECT * FROM emp WHERE deptno = 선택번호;
 *			5. ArrayList<EmpVO> empList 저장
 *			6. 해당 사원 정보 출력 
 * */ 
public class Ex02 {
	public static void main(String[] args) {

		String sql = "SELECT * FROM dept";
		ArrayList<DeptVO> deptList = null;
		ArrayList<EmpVO> empList = new ArrayList<EmpVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		//DeptVO
		int deptno;
		String dname, loc;

		//EmpVO
		int empno;
		String ename;
		String job;
		int mgr;
		LocalDateTime hiredate;
		double sal;
		double comm;

		EmpVO evo = null;
		DeptVO dvo = null;

		conn = DBConn.getConnection();//1+2

		try {

			stmt = conn.createStatement();//가져오는 일꾼 만들어
			rs =stmt.executeQuery(sql);
			if(rs.next()) {
				deptList = new ArrayList<DeptVO>();//record가 하나라도 있을 때만 만들어

				do {
					deptno = rs.getInt("deptno");
					dname = rs.getString("dname");
					loc = rs.getString("loc");

					dvo = new DeptVO(deptno, dname, loc, 0);
					//dvo = new DeptVO();
					//dvo.setDeptno(deptno);
					//dvo.setDeptno(dname);

					//@Builder 쓰는 거랑 밑에꺼랑 같은 코딩이야
					dvo = new DeptVO().builder()
							.deptno(deptno)
							.dname(dname)
							.loc(loc)
							.build();


					deptList.add(dvo);
				}while(rs.next());
				
				//
				deptList.forEach(vo->System.out.println(vo));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("부서번호 입력하세요.");//10
//		deptno = sc.nextInt();
		String deptnos = sc.nextLine();
		sql = String.format("SELECT *"
				+ "FROM emp "
				+ "WHERE deptno IN (%s)", deptnos);//이러면 이제 여러 부서 번호도 처리 가능
		
		//Ex01_04.Java복붙
		try {
			conn = DBConn.getConnection();//singleton이라 있던 거 돌려줘 괜찮아
			stmt = conn.createStatement();
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


				evo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				empList.add(evo);


			}//while
			Ex01_04.dispEmp(empList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//4. connection 객체 닫기 - close()
				rs.close(); // 닫는 순서 중요
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		
		DBConn.close();//4
	}//main
}
