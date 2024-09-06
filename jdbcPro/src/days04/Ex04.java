package days04;

public class Ex04 {
	public static void main(String[] args) {
		//페이징 블럭
//		PREV	[1] 2 3 4 5 6 7 8 9 10	NEXT
//		 <								 >
//		int currentPage = 1;
		int numberOfPageBlock = 10;//한 화면의 페이지가 몇 개냐
		int totalRecords = 151;//총 게시글 수 가정
		int numberPerPage = 10;//한 페이지당 뿌릴 페이지 수
		int totalPages = (int)Math.ceil((double)(totalRecords / numberPerPage));//16
	
		int start, end;
		
		for (int currentPage = 1;  currentPage <= totalPages; currentPage++) {
			
			start = (currentPage-1)/numberOfPageBlock*numberOfPageBlock+1;
			
			end = start + numberOfPageBlock - 1;
			if(end>totalPages) {
				end = totalPages;
			}
			
			System.out.printf("%d\t", currentPage);
			if(start!=1)System.out.print("prev <");
			for (int i = start; i <= end; i++) {
				System.out.printf(i==currentPage?" [%1$d] ":" %1$d ",i);
			}
			if(end!=totalPages)System.out.print("next >");
			System.out.println();
		}
	}//main
}//class
