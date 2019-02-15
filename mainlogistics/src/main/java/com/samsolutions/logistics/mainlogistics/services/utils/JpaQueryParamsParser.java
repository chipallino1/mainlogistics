package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.Map;

@Component
public interface JpaQueryParamsParser {
    Map<String,Object> getParams(Query query);
}
