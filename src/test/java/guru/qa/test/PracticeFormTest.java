package guru.qa.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest(){
        open("/automation-practice-form");
        String eMail = "SBorisoglebsky@gmail.com";
        String phoneNumber ="9165556677";

        $("#firstName").setValue("Serega");
        $("#lastName").setValue("Borisoglebsky");
        $("#userEmail").setValue(eMail);
        $("#userNumber").setValue(phoneNumber);

        $("#genterWrapper").$(byText("Male")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        //BirthDay
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1976");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        //Subject
        $("#subjectsInput").setValue("Hindi").pressEnter();


        $("#uploadPicture").uploadFile(new File("src/test/resources/guru.PNG"));
        $("#currentAddress").setValue("Moscow, Lenina street 2");

        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $("tbody").$(byText("Student Name")).parent().shouldHave(text("Serega Borisoglebsky"));
        $("tbody").$(byText("Student Email")).parent().shouldHave(text(eMail));
        $("tbody").$(byText("Gender")).parent().shouldHave(text("Male"));
        $("tbody").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $("tbody").$(byText("Date of Birth")).parent().shouldHave(text("30 January,1976"));
        $("tbody").$(byText("Subjects")).parent().shouldHave(text("Hindi"));
        $("tbody").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $("tbody").$(byText("Picture")).parent().shouldHave(text("guru.PNG"));
        $("tbody").$(byText("Address")).parent().shouldHave(text("Moscow, Lenina street 2"));
        $("tbody").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));

    }

}
