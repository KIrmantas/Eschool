import java.time.LocalDate;

public class Requires {
    public static class Str {
        public static void notNullOrEmpty(String value, String argumentName) {
            if (value == null | value.isEmpty()) {
                throw new IllegalArgumentException(argumentName + " cannot be null or empty");
            }
        }
    }

    public static class DateTime {
        public static void NotFuture(LocalDate dateTime, String argumentName) {
//            if (dateTime.compareTo(LocalDate.now()) > 0) {
            boolean isFutureDateTime = dateTime.isAfter(LocalDate.now());
            if (isFutureDateTime) {
                throw new FutureBirthdayException(dateTime, argumentName);
            }
        }
    }

    public static class Number<T> {
        public void notNonnegativeNumber(T number, String argumentName) {
            if (number == null || (Double)number <= 0) {
                throw new IllegalArgumentException(argumentName + "cannot be null or nonnegatyve");
            }
        }
    }
}
