package com.examengine.examengine.mongoConfig;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ReadingConverter
public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
    private static final ZoneId EGYPT_ZONE = ZoneId.of("Africa/Cairo");

    @Override
    public LocalDateTime convert(Date date) {
        return date.toInstant().atZone(EGYPT_ZONE).toLocalDateTime();
    }
}
