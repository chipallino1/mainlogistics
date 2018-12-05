package com.samsolutions.logistics.mainlogistics.services.utils;

import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import org.modelmapper.ModelMapper;

public interface Converter {
    default void map(Object src, Object dest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(src, dest);
    }
}
