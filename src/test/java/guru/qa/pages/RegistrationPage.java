package guru.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.component.Calendar;
import org.assertj.core.api.SoftAssertions;

import java.io.File;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationPage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    public void openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }
    public void checkPageName() {
        $("#example-modal-sizes-title-lg").shouldHave(text(RESULTS_TITLE));
    }

    public RegistrationPage typeTestField(String firstName, String lastName, String eMail, String phoneNumber) {
        $("#firstName").val(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(eMail);
        $("#userNumber").setValue(phoneNumber);
        return this;
    }

    public RegistrationPage typeWrapper(String gender, String hobbies) {
        $("#genterWrapper").scrollIntoView(true);
        $("#genterWrapper").$(byText(gender)).click();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage setBirthDay (String day, String month, String year){
        Calendar c = new Calendar();
        c.setDate(day,month,year);
        return this;
    }
    public RegistrationPage setSubject (String subject){
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }
    public RegistrationPage uploadFile (String filePath){
        $("#uploadPicture").uploadFile(new File(filePath));
        return this;
    }
    public RegistrationPage setAddress (String address){
        $("#currentAddress").setValue(address);
        return this;
    }
    public RegistrationPage setStateAndCity (String state, String city){
        $("#state").scrollIntoView(false);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }
    public RegistrationPage clickSubmit (){
        //$("#submit").scrollTo().click();
        $("#submit").scrollIntoView(true);
        $("#submit").click();
        return this;
    }

    public RegistrationPage CheckResults (ElementsCollection lines, Map<String,String> expectedData) {
        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement line: lines) {
            String key = line.$("td").text(); // Student Name
            String expectedValue = expectedData.get(key);
            String actualValue = line.$("td", 1).text();

            softly.assertThat(actualValue)
                    .as(format("Result in line %s was %s, but expected %s", key, actualValue, expectedValue))
                    .isEqualTo(expectedValue);
        }

        softly.assertAll();
        return this;
    }
}
