package systems.miso.systemsdomain.util;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(final String str) {
        return (str == null || str.isEmpty() || str.isBlank());
    }
}
