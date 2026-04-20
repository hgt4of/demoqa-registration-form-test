package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;

public class RegistrationPage {

    // Локаторы
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement phoneInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("[id='react-select-3-input']");
    private final SelenideElement cityInput = $("[id='react-select-4-input']");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");

    // Действия
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        $("label[for='gender-radio-" + getGenderNumber(gender) + "']").click();
        return this;
    }

    public RegistrationPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public RegistrationPage setDateOfBirth( String month, String year, String day ) {
        dateOfBirthInput.click();
        $("[class='react-datepicker__month-select']").click();
        $("[class='react-datepicker__month-select']").selectOptionByValue(month);
        $("[class='react-datepicker__year-select']").click();
        $("[class='react-datepicker__year-select']").selectOptionByValue(year);
        $(".react-datepicker__week:not(.react-datepicker__day--outside-month)")
                .$(byText(day)).click();
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject);
        $(byText(subject)).click();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $("label[for='hobbies-checkbox-" + getHobbyNumber(hobby) + "']").click();
        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String stateName) {
        stateInput.setValue(stateName.substring(0, 3)).pressEnter();
        return this;
    }

    public RegistrationPage setCity(String cityName) {
        cityInput.setValue(cityName.substring(0, 3)).pressEnter();
        return this;
    }

    public RegistrationResultsModal submit() {
        submitButton.scrollTo().shouldBe(visible).click();
        return new RegistrationResultsModal();
    }

    // Радио-баттоны
    private String getGenderNumber(String gender) {
        switch (gender) {
            case "Male": return "1";
            case "Female": return "2";
            case "Other": return "3";
            default: return "1";
        }
    }

    private String getHobbyNumber(String hobby) {
        switch (hobby) {
            case "Sports": return "1";
            case "Reading": return "2";
            case "Music": return "3";
            default: return "1";
        }
    }
}