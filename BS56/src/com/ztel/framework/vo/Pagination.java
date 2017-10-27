/*
 * Copyright (c) 2012,  All rights reserved.
 */
package com.ztel.framework.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.SortControl;

import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;

import com.ztel.framework.util.StringUtils;

/**
 * Value object used in pagination
 * @author Zed
 */
public class Pagination<T> implements Serializable {
	
	/**
	 * Attribute key for request or session
	 */
	public static final String ATTR_KEY = "pagination";
	
	/**
	 * 分页列表当前页码
	 */
	//public static final String PAGE_NO = "pageNo";
	public static final String PAGE_NO = "page";
	
	/**
	 * 当前最大可展示记录条数
	 */
	//public static final String PAGE_SIZE = "pageSize";
	public static final String PAGE_SIZE = "rows";
	
	
	/**
	 * 排序列关键字
	 */
	//public static final String SORT_KEY = "sortKey";
	public static final String SORT_KEY = "sort";
	
	
	/**
	 * 是否升序
	 */
	//public static final String SORT_ASC = "sortAsc";
	public static final String SORT = "order";
	
	
	/**
	 * 每页最大记录数
	 */
	public static final int MAX_PAGE_SIZE = 100;
	
	public static final int DEFALUT_PAGE_SIZE = 10; 

	/** SUID */
	private static final long serialVersionUID = 5738703782174909934L;
	
	/** Number of record  per page */
	private int numPerPage = DEFALUT_PAGE_SIZE;
	
	/** Current page number */
    private int pageNum = 1;
    
    /** Total page */
    private int totalPage;
    
    /** Total record count */
    private int totalCount;
    
    /** Parameter that's used in sql query */
    private Object param = null;
    
    /** Sorted key or label from request and it's not column name */
    private String sortKey;
    
    /** Sorted column name which is converted from sortKey and use it to generate sql */
    private String sortColumn;

    /** Sort by key asc or desc */
	private boolean sortAsc;
	
	/** Record list of current page */
	private List<T> records = null;
	
	/** Request parameter map*/
	private Map<String, String> paramMap = null;
	
	private String sort ;//排序 值为asc 或 desc
	
	/** Pagination request uri that excluded parameters */
	private String uri = "";
    
    public Pagination() {
    }

    /**
     * 
     * @param currentPage
     * @param pageSize
     */
    public Pagination(int numPerPage) {
    	this(1, numPerPage);
    }
    /**
     * 
     * @param currentPage
     * @param pageSize
     */
    public Pagination(int currentPage, int numPerPage) {
        this.pageNum = currentPage;
        this.numPerPage = numPerPage;
    }
    
    /**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

    public int getTotalPage() {
        return totalPage;
    }

//    public void setTotalPage(int totalPage) {
//        this.totalPage = totalPage;
//    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        int p = this.totalCount / this.numPerPage;
        int r = this.totalCount % this.numPerPage;
        this.totalPage = r > 0 ? p + 1 : p;
        //this.pageNum = this.totalPage < this.pageNum ? 1 : this.pageNum;
    }

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
	
	public int getStartIndex() {
		return (this.pageNum - 1) * this.numPerPage;
	}
	
	public int getEndIndex() {
		return this.pageNum  * this.numPerPage - 1;
	}
	
	public boolean isHavingNextPage() {
		return this.totalPage > 0 && this.pageNum < this.totalPage;
	}
	
	public boolean isHavingPreviousPage() {
		return this.totalPage > 0 && this.pageNum > 1;
	}

	/**
	 * @param sortKey the sortKey to set
	 */
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	/**
	 * @return the sortAsc
	 */
	public boolean isSortAsc() {
		return sortAsc;
	}

	/**
	 * @param sortAsc the sortAsc to set
	 */
	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}
	
	

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the records
	 */
	public List<T> getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	

	/**
	 * @param sortColumn the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}
	
	/**
	 * Tranfer the sort key to column, make sure to call it if request contains sortKey
	 * @param mapping
	 */
	public void sortKeyToColumn(Map<String, String> mapping) {
		if (mapping == null || mapping.size() <= 0 || this.sortKey == null) {
			return;
		}
		String _sortColumn = mapping.get(this.sortKey);
		if (_sortColumn != null) {
			this.sortColumn = _sortColumn;
		}
	}

	/**
	 * @return the paramMap
	 */
	public Map<String, String> getParamMap() {
		return paramMap;
	}

	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
		if (this.paramMap != null) {
			this.paramMap.remove(Pagination.PAGE_NO);
		}
	}
	
	public Pagination<T> getNextPage() {
		if (!this.isHavingNextPage()) {
			return null;
		}
		Pagination<T> page = new Pagination<T>();
		page.setPageNum(this.pageNum + 1);
		page.setUri(this.uri);
		page.setParamMap(this.paramMap);
		return page;
	}
	
	public Pagination<T> getPreviousPage() {
		if (!this.isHavingPreviousPage()) {
			return null;
		}
		Pagination<T> page = new Pagination<T>();
		page.setPageNum(this.pageNum - 1);
		page.setUri(this.uri);
		page.setParamMap(this.paramMap);
		return page;
	}
	
	public List<Pagination<T>> getVisualPages() {
		if (this.totalCount <= 0) {
			return null;
		}
		List<Pagination<T>> pages = new ArrayList<>(11);
		int minPage = this.pageNum - 2;
		if (minPage < 1) {
			minPage = 1;
		}
		int maxPage = this.pageNum + 2;
		if (maxPage > this.totalPage) {
			maxPage = this.totalPage;
		}
		for (int i = 1; i <= 2; ++i) {
		    if (i > this.totalPage) {
		    	break;
		    }
		    Pagination<T> page = new Pagination<T>();
		    page.setPageNum(i);
		    page.setUri(this.uri);
		    page.setParamMap(this.paramMap);
		    pages.add(page);
		}
		//[...]
		if (minPage >= 5) {
			pages.add(null);
		}
		for (int i = minPage; i <= maxPage; ++i) {
			if (i == 1 || i == 2) {
				continue;
			}
		    Pagination<T> page = new Pagination<T>();
		    page.setPageNum(i);
		    page.setUri(this.uri);
		    page.setParamMap(this.paramMap);
		    pages.add(page);
		}
		//[...]
		if (maxPage < this.totalPage) {
			pages.add(null);
		}
		return pages;
	}
	

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * Get pagination url
	 * @return
	 */
	public String getUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.uri);
		String queryString = "";
		if (this.paramMap != null && this.paramMap.size() > 0) {
			queryString = StringUtils.getUriParameterString(this.paramMap, true);
		}
		if (this.uri.indexOf('?') == -1) {
			sb.append('?');
		}
		sb.append(Pagination.PAGE_NO).append('=').append(this.pageNum);
		if (queryString.length() > 0) {
			sb.append('&').append(queryString);
		}
		return sb.toString();
	}
	
	/**
	 * Get pagination url
	 * @return
	 */
	public String getUrlWithoutPageNum() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.uri);
		String queryString = "";
		if (this.paramMap != null && this.paramMap.size() > 0) {
			queryString = StringUtils.getUriParameterString(this.paramMap, true);
		}

		if (queryString.length() > 0) {
			if (this.uri.indexOf('?') == -1) {
				sb.append('?').append(queryString);
			}
			else {
				sb.append('&').append(queryString);	
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public <C> Pagination<C> cast(Class<C> clazz) {
		return (Pagination<C>)this;
	}
	
	
}

