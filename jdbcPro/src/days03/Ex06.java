package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

// 로그인 인증처리 
// 아이디 비번 입력
// 로그인 , 회원가입
// 인가는 로그인
public class Ex06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // emp 테이블의 empno가 id라고 가정
        System.out.println("중복 체크할 ID(empno)를 입력?");
        int id = scanner.nextInt(); // 7369 -> 스미스씨 9999 -> 아무도 없음
        String pwd = scanner.next(); // 비밀번호 입력

        String sql = "{call UP_LOGIN(?, ?, ?)}"; // 프로시저 호출

        Connection conn = null;
        CallableStatement cstmt = null;
        int check = -1;

        conn = DBConn.getConnection(); // DB 연결

        try {
            cstmt = conn.prepareCall(sql);

            // IN 파라미터 설정
            cstmt.setInt(1, id);
            cstmt.setString(2, pwd);
            
            // OUT 파라미터 설정
            cstmt.registerOutParameter(3, OracleTypes.INTEGER);

            // 프로시저 실행
            cstmt.executeQuery();

            // OUT 파라미터 값 가져오기
            check = cstmt.getInt(3);

            // 결과에 따른 처리
            if (check == 0) {
                System.out.println("로그인 성공");
            } else if (check == 1) {
                System.out.println("아이디는 존재, 비밀번호가 틀렸습니다.");
            } else if (check == -1) {
                System.out.println("존재하지 않는 ID");
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
}
