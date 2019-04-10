package com.code.read;


public class PageUtil {
    private String page; //页面传回来的页数
    private Integer pageSize; //请求页数 limit 的第二个值

    private Integer count;   //总共几页
    private Integer currentPage; //当前顶几页
    private Integer prevPage; //上一页
    private Integer nextPage; //下一页
    private Integer lastPage; //最后一页
    private Integer startIndex;  //页数的起始值 limit 的第一个值

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(Integer prevPage) {
        this.prevPage = prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "PageUtil [page=" + page + ", pageSize=" + pageSize + ", count="
                + count + ", currentPage=" + currentPage + ", prevPage="
                + prevPage + ", nextPage=" + nextPage + ", lastPage="
                + lastPage + ", startIndex=" + startIndex + "]";
    }

    public PageUtil(String page, Integer pageSize, Integer count) {
        super();
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
        this.currentPage = page==null||page=="" ? 1 : (Integer.valueOf(page));
        this.lastPage = count%pageSize == 0 ? (count/pageSize) : (count/pageSize+1);
        this.prevPage = currentPage==1? 1 : (currentPage-1);
        this.nextPage = currentPage==lastPage ? lastPage : (currentPage+1);
        this.startIndex = (currentPage-1)*pageSize;
    }
}
