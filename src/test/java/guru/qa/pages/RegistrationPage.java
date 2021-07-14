package guru.qa.pages;

import guru.qa.component.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    public void openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
    }
    public RegistrationPage typeTestField(String firstName, String lastName, String eMail, String phoneNumber) {
        $("#firstName").val(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(eMail);
        $("#userNumber").setValue(phoneNumber);
        return this;
    }

    public RegistrationPage typeWrapper(String gender, String hobbies) {

        $("#genterWrapper").$(byText(gender)).click();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage setBirthDay (String day, String month, String year){
        Calendar c = new Calendar();
        c.setDate(day,month,year);
        return this;
    }
}
