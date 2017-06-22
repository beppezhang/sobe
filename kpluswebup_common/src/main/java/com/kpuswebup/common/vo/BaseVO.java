package com.kpuswebup.common.vo;

import java.util.Date;

/**
 * 分页
 * 
 * @author zhuhp
 */
public class BaseVO {

    /**
     * 
     */
    private static final long serialVersionUID = 3134297921370088208L;

    private Long              defaultPageSize  = 15l;
    private Long              pageSize         = 15l;
    private Long              totalCount       = -1l;
    private Long              pageNo           = 1l;
    private Long              pageCount        = 1l;
    private Long              startRow         = 0l;
    private Long              endRow           = 20l;
    private String            orderByClause;
    private Date              startTime;
    private Date              endTime;

    public void doPage() {
        if (pageNo <= 0) this.pageNo = 1l;
        if (pageSize <= 0) this.pageSize = 10l;
        this.startRow = (this.pageNo - 1) * this.pageSize;
        this.endRow = this.pageNo * this.pageSize;
    }

    public void doPage(Long count) {
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

    public Long getStartRow() {
        return startRow;
    }

    public void setStartRow(Long startRow) {
        this.startRow = startRow;
    }

    public Long getEndRow() {
        return endRow;
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

    public void setDefaultPageSize(Long defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

    
    public String getCreator() {
        return creator;
    }

    
    public void setCreator(String creator) {
        this.creator = creator;
    }

    
    public Date getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    public String getModifier() {
        return modifier;
    }

    
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    
    public Date getModifyTime() {
        return modifyTime;
    }

    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    

}
