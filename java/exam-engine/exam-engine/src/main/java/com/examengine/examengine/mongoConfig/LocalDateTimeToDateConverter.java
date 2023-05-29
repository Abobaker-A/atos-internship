package com.examengine.examengine.mongoConfig;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@WritingConverter
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
    private static final ZoneId EGYPT_ZONE = ZoneId.of("Africa/Cairo");

    @Override
    public Date convert(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(EGYPT_ZONE).toInstant());
    }
}
