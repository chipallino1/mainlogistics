package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.samsolutions.logistics.mainlogistics.services.utils.JpaQueryParamsParser;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Service
public class JpaQueryParamsParserImpl implements JpaQueryParamsParser {
    private static String WHERE="where";
    @Override
    public Map<String, Object> getParams(Query query) {
        String queryString = (String)query.getHints().get("org.hibernate.comment");
        String paramName;
        Map<String,Object> paramsMap = new HashMap<>();
        int index=1;
        if(queryString.contains(WHERE))
            queryString = queryString.substring(queryString.indexOf(WHERE)+WHERE.length());
        String[] queryStrings = queryString.split("\\s");
        for(int i=0;i<queryStrings.length;i++){
            if(queryStrings[i].equals("") || queryStrings[i].equals("=") || queryStrings[i].indexOf(':')==0 || queryStrings[i].indexOf('?')==0){
                continue;
            }
            paramName = queryStrings[i];
            while(paramName.indexOf('.')>0){
                paramName=paramName.substring(paramName.indexOf('.')+1);
            }

            paramsMap.put(paramName,query.getParameterValue(index));
            index=index+1;
        }
        return paramsMap;
    }
}
