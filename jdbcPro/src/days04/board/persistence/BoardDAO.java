package days04.board.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;

public interface BoardDAO {

	//	1) 페이징 처리가 되지 않은 게시글 목록 가져와(abstract method)
	ArrayList<BoardDTO> select() throws SQLException;
	//	암것도 없어도 public abstract, interface니까

	//	1-2) 페이징 처리가 된 게시글 목록 가져와
	ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws SQLException;

	//	총 레코드 수(1-3)
	int getTotalRecords() throws SQLException;
	// 총 페이지 수(1-4)
	int getTotalPages(int numberPerPage) throws SQLException;
	// 2) 게시글 쓰기(새 글)
	int insert(BoardDTO dto) throws SQLException;
	// 3) 조회수 증가
	int increaseReaded(long seq)throws SQLException;
	// 3-2) 게시글 상세 보기
	BoardDTO view(long seq)throws SQLException;
//	4) 게시글 삭제
	int delete(long seq)throws SQLException;
//	5) 게시글 수정 - 제목, 내용, 이메일 수정 가능
	int update(BoardDTO dto)throws SQLException;
//	6) 페이징처리 안된 게시글 검색
	ArrayList<BoardDTO> search(String searchCondition, String searchWord) throws SQLException;
//	6-2) 페이징처리 된 게시글 검색
	ArrayList<BoardDTO> search(String searchCondition, String searchWord, int currentPage, int numberPerpage) throws SQLException;
//	검색할 때 총 페이지 수 반환
	int getTotalPages(int numberPerpage, String searchCondition, String searchWord) throws SQLException;
}//interface
