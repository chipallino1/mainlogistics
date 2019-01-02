package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Component
public interface DateConverter {
    default Date getDateFromString(String date){
        Date date1=new Date();
        Calendar calendar=Calendar.getInstance(Locale.CANADA);
        TimeZone.getAvailableIDs();
        ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now());
        return new Date();
    }
}
