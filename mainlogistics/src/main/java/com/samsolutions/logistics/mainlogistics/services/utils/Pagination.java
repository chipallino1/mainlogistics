package com.samsolutions.logistics.mainlogistics.services.utils;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.user.impl.FirmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Pagination<T,V> {
    default PageDTO<T> getPage(List<T> tList, Page<V> page){
        PageDTO<T> pageDTO=new PageDTO<>();
        pageDTO.setListEntitiesDTO(tList);
        pageDTO.setPageNumber(page.getNumber());
        pageDTO.setPageCount(page.getTotalPages());
        return pageDTO;
    }
    /**
     *
     * @param samples Map type because further it will not only 2 params to search
     * @param orderBy order type
     * @param desc desc or asc
     * @return page of V
     */
    Page<V> getOrderPage(Map<String,Object> samples, String orderBy, boolean desc, Pageable pageable,ApplicationContext applicationContext);
}
