package days04;

public class Ex02 {
	public static void main(String[] args) {
		
		int totalRecord = 136;//총 레코드 수가 136이라 가정
		int currentPage = 1;
		int numberPerPage = 10;
//		BETWEEN start AND end;
		int totalPages = (int)Math.ceil((double)(totalRecord / numberPerPage));
		System.out.println(totalPages);
		int start, end;
//		i가 0부터였다면
//		start = i*numberPerPage + 1;
//		end = (i+1)*numberPerPage;
		for (int i = 1; i <= totalPages; i++) {
			start = (i-1)*numberPerPage+1;
			end = start+numberPerPage-1;
			if(end < totalRecord) {
				end = totalRecord;
			}//if
			System.out.printf("%d 페이지 : 시작 = %d, 끝 = %d\n", i, start, end);
		}//for
		
	}
}
