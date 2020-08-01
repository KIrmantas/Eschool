import java.time.LocalDate;

public class FutureBirthdayException extends RuntimeException{
    public FutureBirthdayException(LocalDate futureDate, String argumentName) {
        super(argumentName + " cannot be future. Was " + futureDate);
    }
}
