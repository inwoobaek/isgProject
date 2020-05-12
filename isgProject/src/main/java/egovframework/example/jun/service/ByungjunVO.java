package egovframework.example.jun.service;

public class ByungjunVO {
	
	/** ID */
	private int idx;
	/** 제목 */
	private String title;
	/** 내용 */
	private String contents;
	/** 작성자 */
	private String writer;
	/** 페이지*/
	private int page;

	public ByungjunVO() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public ByungjunVO(int idx, String title, String contents, String writer, int page) {
		super();
		this.idx = idx;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.page = page;
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
	@Override
	public String toString() {
		return "ByungjunVO [idx=" + idx + ", title=" + title + ", contents=" + contents + ", writer=" + writer
				+ ", page=" + page + "]";
	}

}
