package days04.board.vo;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;

public class PagingVO {

//	public int currentPage;
	public int start;
	public int end;
	public boolean prev;
	public boolean next;

	public PagingVO(int currentPage, int numberPerPage, int numberOfPageBlock) {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		//		위에 이거 main에서 어차피 있는데 또 만들어야 돼? main꺼 받아올 방법 없나
		try {
			int totalPages = dao.getTotalPages(numberPerPage);
			start = (currentPage-1)/numberOfPageBlock*numberOfPageBlock+1;
			end = start + numberOfPageBlock - 1;
			if(end>totalPages) {
				end = totalPages;
			}
			if(start!=1) this.prev = true;
			if(end!=totalPages)this.next = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//method를 만들거면 DAO를 member로 injection 받든지 하자
	}


}
