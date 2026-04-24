import org.junit.jupiter.api.Test;
import pages.BaseTest;
import pages.TextBoxPage;

    public class FakerTextboxTests extends BaseTest {

    @Test
    void successTest() {
        // Данные
        String name = faker.name().firstName() + " " + faker.name().lastName();
        String email = faker.internet().emailAddress();
        String currentAddress = faker.address().fullAddress();
        String permanentAddress = faker.address().fullAddress();

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