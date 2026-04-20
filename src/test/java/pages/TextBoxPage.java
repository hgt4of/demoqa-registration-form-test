package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    // Локаторы
    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");

    private final SelenideElement outputName = $("#output #name");
    private final SelenideElement outputEmail = $("#output #email");
    private final SelenideElement outputCurrentAddress = $("#output #currentAddress");
    private final SelenideElement outputPermanentAddress = $("#output #permanentAddress");

    // Действия
    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setUserName(String name) {
        userNameInput.setValue(name);
        return this;
    }

    public TextBoxPage setUserEmail(String email) {
        userEmailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public TextBoxPage setPermanentAddress(String address) {
        permanentAddressInput.setValue(address);
        return this;
    }

    public TextBoxPage submit() {
        submitButton.scrollTo().shouldBe(visible).click();
        return this;
    }

    // Проверки
    public TextBoxPage checkName(String expected) {
        outputName.shouldHave(text(expected));
        return this;
    }

    public TextBoxPage checkEmail(String expected) {
        outputEmail.shouldHave(text(expected));
        return this;
    }

    public TextBoxPage checkCurrentAddress(String expected) {
        outputCurrentAddress.shouldHave(text(expected));
        return this;
    }

    public TextBoxPage checkPermanentAddress(String expected) {
        outputPermanentAddress.shouldHave(text(expected));
        return this;
    }
}