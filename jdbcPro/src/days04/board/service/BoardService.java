package days04.board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;
//service단에서 대부분 transaction 처리
public class BoardService {

	private BoardDAO dao = null;
	//	Constructor DI / Setter DI
	public BoardService(BoardDAO dao) {
		this.dao = dao;
	}

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}


	//1. 게시글 목록 서비스
	//	응답은 여기서 할 게 아니기 때문에 public
	public ArrayList<BoardDTO> selectService(int currentPage, int numberPerpage) {

		ArrayList<BoardDTO> list = null;
		//	1. 실제 DB를 연동해서 ArrayList라는 list 가져와서 return
		try { 	


			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			list = this.dao.select(currentPage, numberPerpage);


			//	2. 누가 가져왔는지 로그 기록 작업
			System.out.println("2. 게시글 목록 : 로그 기록 작업");


			//	3. 다른 작업들 추후에 할 수 있기 때문에 (문자/이메일 전송 등) 
			//	디비 연동 안됐는데 나머지가 되면 안되잖아->트랜잭션
			System.out.println("3. 게시글 목록 : 문자/메일 전송 작업...");
			((BoardDAOImpl)this.dao).getConn().commit();

		} catch (SQLException e) {
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				((BoardDAOImpl)this.dao).getConn().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



		return list;

	}

	//	2. 게시글 쓰기 서비스
	public int insertService(BoardDTO dto) {
		int rowCount = 0;
		try {
			rowCount = this.dao.insert(dto);
			//			2. 로그 기록 작업
			System.out.println("게시글 쓰기 : 로그 기록 작업 ~~~");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			System.out.println("다시 해라 뭔가 잘못됐다");
		}

		return rowCount;
	}

	public BoardDTO viewService(long seq) {
		int rowCount = 0;
		BoardDTO dto = null;
		try {
			//			1. 조회수 증가
			rowCount = this.dao.increaseReaded(seq);
			//			2. 해당 게시글 가져와
			dto = this.dao.view(seq);

			//			3. 로그 기록
			System.out.println("게시글 쓰기 : 로그 기록 작업 ~~~");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			System.out.println("다시 해라 뭔가 잘못됐다");
		}

		return dto;
	}

	public int deleteService(long seq) {
		int rowCount = 0;
		try {
			rowCount = this.dao.delete(seq);
			//			2. 로그 기록
			System.out.println("게시글 삭제 : 로그 기록 작업 ~~~");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			System.out.println("다시 해라 뭔가 잘못됐다");
		}

		return rowCount;
	}
	public int updateService(BoardDTO dto) {
		int rowCount = 0;
		try {
			rowCount = this.dao.update(dto);
			//			2. 로그 기록 작업
			System.out.println("게시글 수정 : 로그 기록 작업 ~~~");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			System.out.println("다시 해라 뭔가 잘못됐다");
		}

		return rowCount;

	}

// 6. 게시글 검색 서비스
	public ArrayList<BoardDTO> searchService(String searchCondition, String searchWord, int currentPage, int numberPerpage) {

		ArrayList<BoardDTO> list = null;
		//	1. 실제 DB를 연동해서 ArrayList라는 list 가져와서 return
		try { 	


			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			list = this.dao.search(searchCondition, searchWord, currentPage, numberPerpage);


			//	2. 누가 가져왔는지 로그 기록 작업
			System.out.println("2. 게시글 검색 : 로그 기록 작업");


			//	3. 다른 작업들 추후에 할 수 있기 때문에 (문자/이메일 전송 등) 
			//	디비 연동 안됐는데 나머지가 되면 안되잖아->트랜잭션
			System.out.println("3. 게시글 검색 : 문자/메일 전송 작업...");
			((BoardDAOImpl)this.dao).getConn().commit();

		} catch (SQLException e) {
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				((BoardDAOImpl)this.dao).getConn().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



		return list;

	}

}//class

