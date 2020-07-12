package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.ofEpochDay(0);
    public static DateTimeFormatter dateFormatter() {
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, Long.parseLong("01"))
                .toFormatter();
    }
}
