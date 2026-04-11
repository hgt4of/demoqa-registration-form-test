import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class SuccessRegistrationForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1280x1024";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void SuccessRegistrationForm() {
        String firstName = "Ruslan";
        String lastName = "Mironov";
        String userEmail = "user" + System.currentTimeMillis() + "@test.com";
        String userNumber = "2222222222";
        String subj = "Biology";
        String currentAddress = "currentAddress";
        String state = "Haryana";
        String city = "Karnal";

        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $("[class='react-datepicker__month-select']").click();
        $("[class='react-datepicker__month-select']").selectOptionByValue("7");
        $("[class='react-datepicker__year-select']").click();
        $("[class='react-datepicker__year-select']").selectOptionByValue("1999");
        $("[class='react-datepicker__day react-datepicker__day--003']").click();
        $("#subjectsInput").setValue(subj);
        $(byText("Biology")).click();
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFromClasspath("avatar.jpg");
        $("#currentAddress").setValue(currentAddress);
        $("[id='react-select-3-input']").setValue("Har").pressEnter();
        $("[id='react-select-4-input']").setValue("Kar").pressEnter();
        $("#submit").click();

        $(byText(firstName+" "+lastName)).shouldBe(visible);
        $(byText(userEmail)).shouldBe(visible);
        $(byText("Male")).shouldBe(visible);
        $(byText(userNumber)).shouldBe(visible);
        $(byText("03 August,1999")).shouldBe(visible);
        $(byText("Biology")).shouldBe(visible);
        $(byText("Sports, Reading, Music")).shouldBe(visible);
        $(byText("avatar.jpg")).shouldBe(visible);
        $(byText("currentAddress")).shouldBe(visible);
        $(byText(state+" "+city)).shouldBe(visible);

    }
}