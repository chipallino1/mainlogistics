package com.samsolutions.logistics.mainlogistics.services.utils;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Pagination<T,V> {
    default PageDTO<T> getPage(List<T> tList, Page<V> page){
        PageDTO<T> pageDTO=new PageDTO<>();
        pageDTO.setListEntitiesDTO(tList);
        pageDTO.setPageNumber(page.getNumber());
        pageDTO.setPageCount(page.getTotalPages());
        return pageDTO;
    }
}
