package guru.qa.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class RegistrationPageWithPageObject {

    RegistrationPage registrationPage = new RegistrationPage();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress();
    String add = faker.address().cityName() + ", " + faker.address().streetName() + ", " + faker.address().streetAddressNumber();
    String phoneNumber = faker.phoneNumber().subscriberNumber(10);

    String
            gender = "Female",
            day = "31",
            month = "January",
            year = "1976",
            subject = "Hindi",
            hobbies = "Music",
            pic = "guru.PNG",
            state = "Haryana",
            city = "Karnal";

    Map<String, String > expectedData = new HashMap<>()
    {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", email);
        put("Gender", gender);
        put("Mobile", phoneNumber);
        put("Date of Birth", day + " " + month + ","+ year);
        put("Subjects", subject);
        put("Hobbies", hobbies);
        put("Picture", pic);
        put("Address", add);
        put("State and City", state + " " + city);

    }};

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest2(){
        registrationPage.openPage();

        registrationPage.typeTestField(firstName, lastName, email, phoneNumber)
                .typeWrapper(gender, hobbies)
                .setBirthDay(day, month, year)
                .setSubject(subject)
                .uploadFile(format("src/test/resources/%s",pic))
                .setAddress(add)
                .setStateAndCity(state, city)
                .clickSubmit();

        registrationPage.checkPageName();

        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();

        registrationPage.CheckResults(lines,expectedData);

    }

}
