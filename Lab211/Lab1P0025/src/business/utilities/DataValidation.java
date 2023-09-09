package business.utilities;

import java.text.SimpleDateFormat;

public final class DataValidation {

    public static void requirePositiveInteger(int value, String msg) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void requireNotNullEmpty(String text, String msg) throws  IllegalArgumentException {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(msg);
        }
    }

}
