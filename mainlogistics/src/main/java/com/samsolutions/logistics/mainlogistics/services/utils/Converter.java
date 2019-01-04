package com.samsolutions.logistics.mainlogistics.services.utils;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

public interface Converter {
    default void map(Object src, Object dest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(src, dest);
    }
    default <T> void mapPayload(Class<T> dto, Map payload){
        Field[] fields = dto.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            String fieldName = fields[i].getName();
            if(payload.containsKey(fieldName)){
                try {
                    fields[i].setAccessible(true);
                    fields[i].set( fields[i].getType().newInstance() ,payload.get(fieldName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
