package i.am.whp.util;

/**
 * @author wuhepeng
 * @date 2019/9/3
 */
public class PageCondition {
    private Integer currentPage;
    private Integer pageSize;
    private Integer startNum;

    public Integer getCurrentPage() {
        if (currentPage == null) {
            return 1;
        }
        return currentPage < 1 ? 1 : currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 10;
        }
        return pageSize < 1 ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartNum() {
        return (this.getCurrentPage() - 1) * this.getPageSize();
    }
}
