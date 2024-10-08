package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.doit.domain.EmpDeptSalgrade;
import org.doit.domain.EmpVO;

import com.util.DBConn;

/*emp와 dept와 salgrade를 join한 결과를 원해-> 전용 VO를 만들어(EmpDeptSalgradeVO라는 이름을 줄 거야)
 * */
public class Ex03 {
	public static void main(String[] args) {
//		Ex01_04.main()에 있는 모든 코딩을 복붙했어 수정만 할거야
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT empno, ename, dname, hiredate , sal+NVL(comm,0) pay, grade "
				+ " FROM emp e JOIN dept d ON e.deptno = d.deptno "
				+ "            JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal";
		int empno;
		String ename;
		LocalDateTime hiredate;// 이게 제일 나을 듯(여러 메소드 지원)
		double pay;
		String dname;
		int grade;
		//			테이블 attribute에 따라 변수 다 줘

		ArrayList<EmpDeptSalgrade> list = new ArrayList<>();
		EmpDeptSalgrade vo = null;


		try {
			//			1+2작업 == com.util.DBConn.getConnection()으로 만들어둠!
			conn = DBConn.getConnection();
			//				3. 원하는 작업(CRUD)-statement 객체
			stmt = conn.createStatement();
			//				stmt.executeUpdate(sql); 영향 받은 record 갯수를 돌려줘서 return type이 int(insert, update, delete)
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				dname = rs.getString("dname");
				hiredate = rs.getTimestamp("hiredate").toLocalDateTime();
				pay = rs.getDouble("pay");
				grade = rs.getInt("grade");


				vo = new EmpDeptSalgrade(empno, ename, hiredate, pay, ename, grade);
				list.add(vo);


			}//while
			list.forEach(evo->System.out.println(evo));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//4. connection 객체 닫기 - close()
				rs.close(); // 닫는 순서 중요
				stmt.close();
				//				conn.close();
				DBConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();



			}
		}
	}//main
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void dispEmp(ArrayList<EmpVO> list) {
		if(list.size()==0) {
			System.out.println("사원 없어요");
			return;
		}//사원 정보 출력

		//		2번

		list.forEach(vo-> {
			System.out.printf("%d\t"
					+ "%s\t"
					+ "%s\t"
					+ "%d\t"
					+ "%tF\t"
					+ "%.2f\t"
					+ "%.2f\t"
					+ "%d\n", vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr(),vo.getHiredate(), vo.getSal(), vo.getComm(), vo.getDeptno());//null은 0으로 나와
			System.out.println(vo.toString());
		});



		//		1번
		//		Iterator<EmpVO> ir = list.iterator();
		//		
		//		while(ir.hasNext()) {
		//			EmpVO vo = ir.next();
		//			System.out.printf("%d\t"
		//									+ "%s\t"
		//									+ "%s\t"
		//									+ "%d\t"
		//									+ "%tF\t"
		//									+ "%.2f\t"
		//									+ "%.2f\t"
		//									+ "%d\n", vo.getEmpno(), vo.getEname(), vo.getJob(), vo.getMgr(),vo.getHiredate(), vo.getSal(), vo.getComm(), vo.getDeptno());//null은 0으로 나와
		//			System.out.println(vo.toString());
		//}//while
	}
}
