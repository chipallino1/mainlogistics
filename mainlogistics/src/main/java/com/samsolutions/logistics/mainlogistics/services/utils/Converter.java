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

}
