package com.samsolutions.logistics.mainlogistics.dao.pagination;

import com.samsolutions.logistics.mainlogistics.services.utils.PageDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageDaoImpl<T> implements PageDao<T> {
    @Override
    public void setContent() {

    }

    @Override
    public List<T> getContent() {

        return null;
    }

    @Override
    public Long getPagesCount() {
        return null;
    }

    @Override
    public Long getElementsOnPage() {
        return null;
    }
}
