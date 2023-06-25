package com.fodala.paging;

public class PageItem {
    private PageItemType pageItemType;
    private int index;
    private boolean active;

    public PageItem(PageItemType pageItemType, int index, boolean active) {
        this.pageItemType = pageItemType;
        this.index = index;
        this.active = active;
    }

    public PageItem() {
    }

    public PageItemType getPageItemType() {
        return pageItemType;
    }

    public void setPageItemType(PageItemType pageItemType) {
        this.pageItemType = pageItemType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
