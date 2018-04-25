package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString) {
        try {
            //return DATE_FORMATTER.parse(dateString, LocalDate::of);

            //Date convertedDate = simpleDateFormat.parse(dateString);

            return LocalDate.parse(dateString, DATE_FORMATTER);

        } catch (DateTimeParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validDate(String dateString) {
        // Пытаемся разобрать строку.
        return DateUtil.parse(dateString) != null;
    }

}
