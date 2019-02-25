package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.samsolutions.logistics.mainlogistics.services.utils.JpaQueryParamsParser;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import java.util.*;

@Service
public class JpaQueryParamsParserImpl implements JpaQueryParamsParser {
    private static String WHERE="where";
    private Map<String,String[]> rulesMap = new HashMap<>();

    @PostConstruct
    public void init(){
        rulesMap.put("K",new String[]{"E","O"});
        rulesMap.put("E",new String[]{"V"});
        rulesMap.put("O",new String[]{"K"});
    }

    @Override
    public Map<String, Object> getParams(Query query) {
        String queryString = (String)query.getHints().get("org.hibernate.comment");
        String paramName;
        Map<String,Object> paramsMap = new HashMap<>();
        int index=1;
        if(queryString.contains(WHERE))
            queryString = queryString.substring(queryString.indexOf(WHERE)+WHERE.length());
        else
            return null;
        String[] queryStrings = queryString.split("\\s");
        checkRightSplit(queryStrings);

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
    private Map<String,String> checkRightSplit(String[] splitted){
        Map<String,String> rulesConformity = new HashMap<>();
        for(int i=0;i<splitted.length;i++){
            if(splitted[i].equals("=")){
                rulesConformity.put(splitted[i],"E");
                continue;////////////////////
            }
            if(splitted[i].equals("or") || splitted[i].equals("and")){
                rulesConformity.put(splitted[i],"O");
                continue;
            }
            if(splitted[i-1].equals("=")){
                rulesConformity.put(splitted[i],"V");
                continue;
            }
            rulesConformity.put(splitted[i],"P");

        }
        return rulesConformity;
    }
    private boolean dynamicOrEqualsString(String value,String[] valuesToCompare){
        for(int i=0;i<valuesToCompare.length;i++){
            if(value.equals(valuesToCompare[i])){
                return true;
            }
        }
        return false;
    }
    private void checkRules(Map<String,String> rulesConformity,Map<String,String[]> rulesMap){
        for(int i=0;i<rulesConformity.size();i++){
           // rulesConformity.get
        }
    }
}
