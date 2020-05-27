package egovframework.example.jun.web.service.Paging;

/**
 * @Class Name : Criteria.java
 * @Description : Criteria Class
 * @Modification Information
 * @
 * @  수정일                    수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.27     이병준            코멘트 작성
 *
 * @author 인실리코젠 BS팀
 * @since 2020.05.27
 */

/**
 * 페이징을 위한 기준
 * 
 * @param -
 * @return -
 * @exception -
 * @comment 페이징을 서브하는 기준점을 잡기위한 클래스
 */
public class Criteria {
	private int page;
	private int perPageNum;

	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		if (pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}
}
