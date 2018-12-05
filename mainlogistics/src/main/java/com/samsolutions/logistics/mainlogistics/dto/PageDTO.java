package com.samsolutions.logistics.mainlogistics.dto;

import java.io.Serializable;
import java.util.List;

public class PageDTO<T> implements Serializable {
    private List<T> listEntitiesDTO;
    private int pageNumber;
    private int pageCount;

    public List<T> getListEntitiesDTO() {
        return listEntitiesDTO;
    }

    public void setListEntitiesDTO(List<T> listEntitiesDTO) {
        this.listEntitiesDTO = listEntitiesDTO;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
