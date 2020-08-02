package peaple;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.JSR310StringParsableDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.log4j.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    private static final Logger logger = LogManager.getLogger("main logger");
    private static final String format = "%d{yyyy-MM-dd HH:mm:ss,SSS} [%t] %p - %m%n";
    private static final String logFile = "C:\\Users\\Irmantas\\Desktop\\Java\\Eschool\\log.txt";
    private static final String dataFileJson = "C:\\Users\\Irmantas\\Desktop\\Java\\Eschool\\MOCK_DATA.json";
    private static final String rezultDataFileJson = "C:\\Users\\Irmantas\\Desktop\\Java\\Eschool\\rezult.json";

    public static void main(String[] args) {

        buuildLogger();
        String json = "[\n" +
                "  {\n" +
                "    \"firstName\" : \"Jonas\",\n" +
                "    \"lastName\" : \"Jonaitis\",\n" +
                "    \"birthday\" : {\"year\":1985,\"month\":12,\"day\":5},\n" +
                "    \"weight\" : 68.2,\n" +
                "    \"height\" : 1.87,\n" +
                "    \"gender\" : \"MAN\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\" : \"Asta\",\n" +
                "    \"lastName\" : \"Astaite\",\n" +
                "    \"birthday\" : {\"year\":2018,\"month\":7,\"day\":25},\n" +
                "    \"weight\" : 68.2,\n" +
                "    \"height\" : 1.87,\n" +
                "    \"gender\" : \"MAN\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\" : \"Petras\",\n" +
                "    \"lastName\" : \"Pettraitis\",\n" +
                "    \"birthday\" : {\"year\":1999,\"month\":6,\"day\":15},\n" +
                "    \"weight\" : 68.2,\n" +
                "    \"height\" : 1.87,\n" +
                "    \"gender\" : \"MAN\"\n" +
                "  }\n" +
                "]";

        demoPerson();
    }

    private static void buuildLogger() {
        PatternLayout pattern = new PatternLayout(format);

        addConsoleAppender(pattern);
        addFileAppender(pattern);

    }

    private static void addConsoleAppender(PatternLayout pattern) {
        FileAppender fileAppender = null;
        try {
            fileAppender = new FileAppender(pattern, logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addAppender(fileAppender);
    }

    private static void addFileAppender(PatternLayout pattern) {
        ConsoleAppender consoleAppender = new ConsoleAppender(pattern);
        logger.addAppender(consoleAppender);
    }

    public static List<Person> readDataFromFileJson() {

        List<Person> personList = null;
        try (FileReader fileReader = new FileReader(dataFileJson)){
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormat));

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(javaTimeModule);
            mapper.registerModule(new JSR310Module());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            personList = Arrays.asList(mapper.readValue(fileReader, Person[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }

    private static void writeDataToFileJson(List<Person> personList) {

        try (FileWriter file = new FileWriter(rezultDataFileJson)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
            mapper.setDateFormat(dateFormat);
            mapper.writeValue(file, personList);
            logger.info(mapper.writeValueAsString(personList.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void demoPerson() {

        logger.info("Start logger");

        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("Jonas", "Jonaitis",
                LocalDate.of(1985, 12, 5),
                68.2f, 1.87f, Gender.MAN);
        Person person2 = new Person("Asta", "Astaite",
                LocalDate.of(1980, 7, 25),
                56.4f, 1.6f, Gender.WOMAN);
        Person person3 = new Person("Petras", "Pettraitis",
                LocalDate.of(2003, 7, 20),
                67f, 1.74f, Gender.MAN);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        writeDataToFileJson(personList);

//        List<Person> personList1 = readDataFromFileJson();
//        System.out.println(personList1);

//        System.out.println(personList.stream()
//                .map(Person::toString)
//                .collect(Collectors.joining("\n")));
//
//        double peopleAgeAverage = personList.stream().mapToInt(Person::getAge).average().getAsDouble();
//        System.out.printf("Person metu vidurkis: %4.2f\n", peopleAgeAverage);
//
//        System.out.println("--------------------------");
//        List<Person> personsAge18 = personList.stream()
//                .filter(x -> Period.between(x.getBirthday(), LocalDate.now()).getYears() >= 18)
//                .collect(Collectors.toList());
//
//        personsAge18.forEach(System.out::println);
//
//        System.out.println("--------------------------");
//        Person[] peoples = {person1, person2, person3};
//
//        Gson gson = new Gson();
//        String peopleJson = gson.toJson(person1);
//        logger.info(peopleJson);
//        Stream<Person> peoplesStream = Arrays.stream(peoples);
//        peoplesStream.filter(x -> x.getAge() >= Person.ADULT_YEAR)
//                .forEach(p -> {
//                    System.out.println(p);
//                    logger.info("Person is adult: ");
//                    logger.info(p);
//                });
//

        logger.info("End logger");
    }
}
