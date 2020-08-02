package peaple;

import java.time.LocalDate;

public class Requires {
    public static class Str {
        public static void notNullOrEmpty(String value, String argumentName) {
            if (value == null || value.trim().isEmpty()) {
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

    public static class NumbersCompare {
        public static <T extends Number & Comparable<T>> void compareNumber(T number1, T number2, String argumentName) {
            if (number1 == null || number2 == null || number1.compareTo(number2) <= 0) {
                throw new IllegalArgumentException(argumentName + " cannot be null or nonnegatyve");
            }
        }
    }
}
