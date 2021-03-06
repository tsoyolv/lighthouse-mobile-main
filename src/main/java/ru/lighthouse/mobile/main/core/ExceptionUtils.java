package ru.lighthouse.mobile.main.core;

public final class ExceptionUtils {
    private ExceptionUtils() {
    }

    public static String getCauseMessage(Throwable throwable) {
        return getCause(throwable).getMessage();
    }

    public static String getCauseLocalizedMessage(Throwable throwable) {
        return getCause(throwable).getLocalizedMessage();
    }

    public static Throwable getCause(Throwable throwable) {
        if (throwable.getCause() == null) {
            return throwable;
        }
        return getCause(throwable.getCause());
    }
}
