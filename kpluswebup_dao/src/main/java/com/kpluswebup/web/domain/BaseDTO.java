package com.kpluswebup.web.domain;

/**
 * 分页
 * 
 * @author zhuhp
 */
public class BaseDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 3134297921370088208L;

    /**
     * 默认显示条数
     */
    private Long              defaultPageSize  = 16l;
    /**
     * 自定义条数
     */
    private Long              pageSize         = 16l;

    /**
     * 总条数
     */
    private Long              totalCount       = -1l;

    /**
     * 页码
     */
    private Long              pageNo           = 1l;

    /**
     * 总页码
     */
    private Long              pageCount        = 1l;

    private Long              startRow         = 0l;

    private Long              endRow           = 20l;

    /**
     * 排序条件 orderByClause＝"order by  id desc"
     */
    private String            orderByClause;

    public void doPage() {
        if (pageNo <= 0) this.pageNo = 1l;
        if (pageSize <= 0) this.pageSize = 10l;
        this.startRow = (this.pageNo - 1) * this.pageSize;
        this.endRow = this.pageNo * this.pageSize;
    }

    public void doPage(Long count) {
        if (count == null) {
            count = 0l;
        }
        this.totalCount = count;
        if (pageSize <= 0) this.pageSize = defaultPageSize;
        Long mod = -1l;
        if (totalCount != -1l) {
            if (totalCount > pageSize) {
                mod = totalCount % pageSize;
                if (mod != 0) {
                    pageCount = (totalCount / pageSize) + 1;
                } else {
                    pageCount = (totalCount / pageSize);
                }
            } else {
                pageCount = 1l;
            }
        }
        if (pageNo <= 0) this.pageNo = 1l;
        if (pageNo > pageCount) this.pageNo = pageCount;
        this.startRow = (this.pageNo - 1) * this.pageSize;
        this.endRow = this.pageNo * this.pageSize;
    }

    public void doPage(Long count, Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.doPage(count);
    }

    public boolean isFirstPage() {
        return this.getPageNo() == 1;
    }

    public boolean isLastPage() {
        return this.getPageCount() == this.getPageNo();
    }

    public void setCurrentPage(Long cPage) {
        this.pageNo = ((cPage == null) || (cPage <= 0)) ? 1 : cPage;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long iPageNo) {
        this.pageNo = iPageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long iPageSize) {
        if (iPageSize >= 1) {
            this.pageSize = iPageSize;
        }
    }

    public Long getTotalCount() {
        return this.totalCount == -1 ? 0 : totalCount;
    }

    public void setTotalCount(Long iTotalCount) {
        if (iTotalCount > 0) {
            this.totalCount = iTotalCount;
        }
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long iPageCount) {
        this.pageCount = iPageCount;
    }

    public boolean isHasNextPage() {
        return this.getPageNo() < this.getPageCount();
    }

    public boolean isHasPrevPage() {
        return this.getPageNo() > 1;
    }

    public Long getPrePage() {
        return (this.pageNo < 2) ? 1 : this.pageNo - 1;
    }

    public Long getNextPage() {
        return (this.pageNo >= this.pageCount) ? this.pageCount : this.pageNo + 1;
    }

    //修改成即时计算开始行数
    public Long getStartRow() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public void setStartRow(Long startRow) {
        this.startRow = startRow;
    }

    public Long getEndRow() {
    	return this.pageNo * this.pageSize;
    }

    public void setEndRow(Long endRow) {
        this.endRow = endRow;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public Long getDefaultPageSize() {
        return defaultPageSize;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
