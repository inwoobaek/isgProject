package egovframework.example.jun.web.service;

/**
 * @Class Name : ByungjunVO.java
 * @Description : ByungjunVO Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2020.05.27 이병준 코멘트 작성
 *
 * @author 인실리코젠 BS팀
 * @since 2020.05.27
 */

public class ByungjunVO {

	/** ID */
	private int idx;
	/** 제목 */
	private String title;
	/** 내용 */
	private String contents;
	/** 작성자 */
	private String writer;
	/** 페이지 */
	private int page;
	/** 링크 */
	private String href;

	public ByungjunVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ByungjunVO(int idx, String title, String contents, String writer, int page, String href) {
		super();
		this.idx = idx;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.page = page;
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "ByungjunVO [idx=" + idx + ", title=" + title + ", contents=" + contents + ", writer=" + writer
				+ ", page=" + page + ", href=" + href + "]";
	}

}
