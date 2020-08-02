package peaple;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.Period;

public class Person {

    public static final int ADULT_YEAR = 18;
    private String firstName;
    private String lastName;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birthday;
    private float weight;
    private float height;
    private Gender gender;

    public Person(String firstName, String lastName, LocalDate birthday,
                  float weight, float height, Gender gender) {

        setFirstName(firstName);
        setLastName(lastName);
        Requires.DateTime.NotFuture(birthday, "Birthday");
        this.birthday = birthday;
        setWeight(weight);
        setHeight(height);
        this.gender = gender;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        Requires.Str.notNullOrEmpty(firstName, "First name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        Requires.Str.notNullOrEmpty(lastName, "Last name");
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        Requires.NumbersCompare.compareNumber(weight, 0.f, "Weight");
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        Requires.NumbersCompare.compareNumber(weight, 0.f, "Height");
        this.height = height;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", height=" + height +
                ", gender=" + gender +
                '}';
    }

}
