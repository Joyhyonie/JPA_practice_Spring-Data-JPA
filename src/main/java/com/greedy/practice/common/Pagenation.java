package com.greedy.practice.common;

import org.springframework.data.domain.Page;

public class Pagenation {
	
	public static PagingButtonInfo getPagingButtonInfo(Page page) {
		
		int currentPage = page.getNumber() + 1;	 /* 인덱스 기준이므로 +1 */
		int defaultButtonCount = 10;
		int startPage;
		int endPage;
		
		startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
		endPage = startPage + defaultButtonCount - 1;
		
		/* 내가 가진 총 페이지 수가 4일 경우, endPage(10)보다 작으므로 endPage를 4로 변경하는 로직 */
		if(page.getTotalPages() < endPage)
			endPage = page.getTotalPages();
		
		if(page.getTotalPages() == 0 && endPage == 0)
			endPage = startPage;
		
		return new PagingButtonInfo(currentPage, startPage, endPage);
	}

	
	
	
	
	
	
	
	
	
	
	
}
