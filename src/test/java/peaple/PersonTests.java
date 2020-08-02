package peaple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTests {

    // act given when then
    //
    //
    @Test
    void getAge_when_person_bornToday_returns_0() {
        // Arrange
        Person person = new Person("a", "a", LocalDate.now(), 1, 1, Gender.MAN);
        // Act
        int age = person.getAge();
        // assert
        assertThat(age).isEqualTo(0);
//        assertEquals(0, age);
    }

    @Test
    void new_given_nullFirstName_throws_IllegalArgumentException() {
        // Arrange
        String firstName = null;
        // Act
        Throwable thrown = catchThrowable(() -> buildPersonWithFirstName(firstName, "B", LocalDate.now()));
        // Assert
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("First name");

    }

    @ParameterizedTest(name = "\"{0}\"- throws IllegalArgumentException")
    @ValueSource(strings = {"", " "})
    @NullSource
//    @MethodSource
    void new_given_nullLastName_throws_IllegalArgumentException(String lastName) {
        // Act
        Throwable thrown = catchThrowable(() -> buildPersonWithFirstName("A", lastName, LocalDate.now()));
        // Assert
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Last name");
    }

    @Test
    void new_given_birthdate_is_leter_3000_01_01_throws_IllegalArgumentException() {
        // Act
        LocalDate birthDate = LocalDate.of(3000, 1, 1);
        Throwable thrown = catchThrowable(() -> buildPersonWithFirstName("A", "B", birthDate));
        // Assert
        assertThat(thrown)
                .isInstanceOf(FutureBirthdayException.class)
                .hasMessage("Birthday cannot be future. Was %s", birthDate);

    }

    @Test
    void getFullName_when_firstName_and_lastName_is_goods_return_sum_firstName_space_lastName() {
        String firstName = "Anna";
        String lastName = "Barbara";
        Person person = new Person(firstName, lastName, LocalDate.now(), 1, 1, Gender.MAN);

        String fullname = person.getFullName();
        assertEquals(firstName + " " + lastName, fullname);
    }

    private static void buildPersonWithFirstName(String firstName, String lastName, LocalDate birhtDate) {
        new Person(firstName, lastName, birhtDate, 1, 1, Gender.MAN);
    }

}
