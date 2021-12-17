package ru.ekitselyuk.kotlinforbegginers.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {

    private StringUtils() {
    }

    public static String toDateString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(date);
    }
}
