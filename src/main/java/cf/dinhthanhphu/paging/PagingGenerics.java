package cf.dinhthanhphu.paging;

import java.util.ArrayList;
import java.util.List;

public class PagingGenerics<T> {
	
	private int page;
	private int totalPage;
	private List<T> listResult = new ArrayList<>();
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	
	
	
	
}
