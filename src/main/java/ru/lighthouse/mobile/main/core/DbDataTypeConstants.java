package ru.lighthouse.mobile.main.core;


import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class DbDataTypeConstants {
    private DbDataTypeConstants() {
    }

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
    public static final RoundingMode ROUND_MODE = RoundingMode.CEILING;
    public static final int MONEY_SCALING = 2;
}