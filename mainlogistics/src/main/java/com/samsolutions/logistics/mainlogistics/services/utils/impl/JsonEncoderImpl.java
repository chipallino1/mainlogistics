package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.google.gson.Gson;
import com.samsolutions.logistics.mainlogistics.services.utils.JsonEncoder;
import org.springframework.stereotype.Service;

@Service
public class JsonEncoderImpl implements JsonEncoder {
    @Override
    public String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
