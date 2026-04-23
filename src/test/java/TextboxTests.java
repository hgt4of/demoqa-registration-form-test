
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.BaseTest;
import pages.TextBoxPage;

    public class TextboxTests extends BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

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