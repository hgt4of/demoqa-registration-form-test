
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import base.BaseTest;
import pages.TextBoxPage;

    public class TextboxTests extends BaseTest {

    @Test
    void successTest() {
        // Данные
        String name = "Ruslan Mironov";
        String email = "rus@gmail.com";
        String currentAddress = "address";
        String permanentAddress = "another address";

        new TextBoxPage()
                // Действия
                .openPage()
                .setUserName(name)
                .setUserEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit()

                // Проверки
                .checkName(name)
                .checkEmail(email)
                .checkCurrentAddress(currentAddress)
                .checkPermanentAddress(permanentAddress);
    }
}