package com.samsolutions.logistics.mainlogistics.services.utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public interface JsonEncoder {
    String toJson(Object object);
}
