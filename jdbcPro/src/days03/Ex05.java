package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class Ex05 {
	public static void main(String[] args) {
		// [저장 프로시저]   - 입력받은 ID를 사용 여부 체크하는 프로시저
		//       ㄴ 회원가입
		//             아이디 : [   hong     ] <ID중복체크버튼>
		//             비밀번호      
		//             이메일
		//             주소
		//             연락처
		//             등등

		//		emp table의 empno가 id라고 가정
		Scanner sc = new Scanner(System.in);
		System.out.println("중복체크 할 ID(empno) 입력");
		int id = sc.nextInt();//7369 -> smith니까 사용중이라고 떠야 해, 9999는 사용 가능

		//		UP_IDCHECK 프로시저를 cstmt 사용해서 처리(callable statement)
		//		String sql = "{ call 저장 프로시저명 }";
		String sql = "{ call Up_idcheck(pid=>?, pcheck=>?)";

		Connection conn = null;
		CallableStatement cstmt = null;
		int check = -1;

		conn = DBConn.getConnection();

		try {
			cstmt = conn.prepareCall(sql);
			//			IN ?, OUT ?
			cstmt.setInt(1, id);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			cstmt.executeQuery();
			check = cstmt.getInt(2);//출력용을 가져와서 check에 담아
			if(check == 0) {
				System.out.println("사용 가능");
			}else if(check ==1) {
				System.out.println("사용 불가");
			}
			//ResultSet 안 받아도 되는 이유 : 출력용 parameter가 있기 때문
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		DBConn.close();

	}//main

}//class
