package guru.qa.pages;

import com.codeborne.selenide.Configuration;
import org.apache.commons.codec.language.MatchRatingApproachEncoder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class RegistrationPageWithPageObject {

    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = "Serega",
            lastName = "Borisoglebsky",
            email = "SBorisoglebsky@gmail.com",
            phoneNumber = "9165556677",
            gender = "Male",
            day = "30",
            month = "January",
            year = "1976",
            subject = "Hindi",
            hobbies = "Music",
            pic = "guru.PNG",
            add = "Moscow, Lenina street 2",
            state = "Haryana",
            city = "Karnal"
                    ;

    Map <String, String > expectedValue = new HashMap<>();
    {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", email);
        put("Gender", gender);
        put("Mobile", phoneNumber);
        put("Date of Birth", day + " " + month + ", "+ year);
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

        registrationPage.typeTestField(firstName, lastName, email, phoneNumber);

        Map<Integer, String> states = new HashMap<Integer, String>();
        states.put(1, "Germany");


    }

}
