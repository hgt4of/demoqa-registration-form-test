package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {

    // Локаторы
    private final SelenideElement modal = $(".modal-content");
    private final SelenideElement modalTitle = $(".modal-title");

    // Проверки
    public RegistrationResultsModal shouldBeVisible() {
        modal.shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal shouldNotBeVisible() {
        modal.shouldNotBe(visible);
        return this;
    }

    public RegistrationResultsModal checkResult(String expectedText) {
        modal.shouldHave(visible);
        $(byText(expectedText)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkStudentName(String firstName, String lastName) {
        $(byText(firstName + " " + lastName)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkEmail(String email) {
        $(byText(email)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkGender(String gender) {
        $(byText(gender)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkPhone(String phone) {
        $(byText(phone)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkDate(String date) {
        $(byText(date)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkSubject(String subject) {
        $(byText(subject)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkHobbies(String hobbies) {
        $(byText(hobbies)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkPicture(String fileName) {
        $(byText(fileName)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkAddress(String address) {
        $(byText(address)).shouldBe(visible);
        return this;
    }

    public RegistrationResultsModal checkStateAndCity(String state, String city) {
        $(byText(state + " " + city)).shouldBe(visible);
        return this;
    }
}