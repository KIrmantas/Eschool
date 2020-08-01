import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Demo {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("Jonas", "Jonaitis", LocalDate.of(1985, 12, 5),
                86.5f, 1.87f, Gender.MAN);
        Person person2 = new Person("Asta", "Astaite", LocalDate.of(1980, 7, 25),
                56.4f, 1.6f, Gender.WOMAN);
        Person person3 = new Person("Petras", "Pettraitis", LocalDate.of(2003, 7, 20),
                67f, 1.74f, Gender.MAN);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        System.out.println(personList.stream()
                .map(Person::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("--------------------------");
        List<Person> personsAge18 = personList.stream()
                .filter(x -> Period.between(x.getBirthday(), LocalDate.now()).getYears() >= 18)
                .collect(Collectors.toList());

        personsAge18.forEach(System.out::println);

        System.out.println("--------------------------");
        System.out.println("--------------------------");
        Person[] peoples = {person1, person2, person3};
        Stream<Person> peoplesStream = Arrays.stream(peoples);
        peoplesStream.filter(x -> x.getAge() >= Person.ADULT_YEAR)
                .forEach(System.out::println);

    }
}
