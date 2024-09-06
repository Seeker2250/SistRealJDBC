package days04;

public class Ex03 {
	public static void main(String[] args) {
		/* 게시글 상세보기 처리과정 설명
		 * 
		 * Contoller에서 service로부터 dto를 한 번에 받아서 리턴
		 * service에서는 dao에 있는 메소드를 끌어다가 만들어서 dto를 리턴해
		 * 근데 dao는 인터페이스니까 이 메소드의 구현은 dao를 implement한 class에서 이루어짐
		 * dto 넘겨줘야 처리하니까 당연히 dto 리턴
		 * 어떤 글을 보는지 알아야 하니까 글 번호를 입력 받는 것이고
		 * 상세보기에서 출력될 내용들을 받아와야 하는데
		 * 하나하나 하는 것보다는 dto로 한 번에 처리하는 게 나으니까 dto를 받아오는 것
		 * 그리고 논리적으로 게시글을 보면 조회수도 올라가야 하니까 그것도 그냥 글 번호 받아와서
		 * 해당 조회수 증가시키는 메소드로 처리
		 * dao가 db랑 연동되어있으니 당연히 service는 dao로부터 의존성 주입 받아야 함~
		 * 
		 * Ex01					BoardController			BoardService											BoardDAOImpl				Oracle 연동
		 * 	main()			->	boardStart()
		 * 						 ㄴ메뉴출력()
		 * 						 ㄴ메뉴선택() n번 입력
		 *						 ㄴ메뉴처리()
		 *						 	ㄴ상세보기()	->  BoardDTO viewService(입력받은 seq)			->				int increaseReaded(seq)
		 *													트랜잭션 처리											BoardDTO view(seq)
		 *							ㄴ글 번호 입력(seq)		1) 조회수 증가
		 *													2) 게시글 정보+로그 기록
		 * 						BoardDTO dto = service.viewService(seq)
		 * 						게시글 정보 출력
		 * 
		 * 							검색하기()	->			ArrayList(BoardDTO)searchService(검색조건, 검색어)		search(검색조건, 검색어)
		 * 													여러 값 돌려줘야 하니까 ArrayList구조로 dto return
		 * 							검색조건, 검색어 입력
		 * 							출력( 목록 보기 복붙)
		 * 
		 * 
		 * 
		 * */
	}
}
