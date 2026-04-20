import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.RegistrationResultsModal;

public class SuccessRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fullRegistrationTest() {
        // Данные
        String firstName = "Ruslan";
        String lastName = "Mironov";
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String phone = "2222222222";
        String subject = "Biology";
        String address = "currentAddress";
        String state = "Haryana";
        String city = "Karnal";

        // Действия
        RegistrationResultsModal modal = new RegistrationPage()
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender("Male")
                .setPhone(phone)
                .setDateOfBirth("7", "1999", "3")
                .setSubject(subject)
                .setHobby("Sports")
                .setHobby("Reading")
                .setHobby("Music")
                .uploadPicture("avatar.jpg")
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        // Проверки
        modal.shouldBeVisible()
                .checkStudentName(firstName, lastName)
                .checkEmail(email)
                .checkGender("Male")
                .checkPhone(phone)
                .checkDate("03 August,1999")
                .checkSubject(subject)
                .checkHobbies("Sports, Reading, Music")
                .checkPicture("avatar.jpg")
                .checkAddress(address)
                .checkStateAndCity(state, city);
    }

    @Test
    void minimalDataRegistrationTest() {
        // Данные
        String firstName = "Ivan";
        String lastName = "Petrov";
        String phone = "9998887766";

        // Действия
        RegistrationResultsModal modal = new RegistrationPage()
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender("Male")
                .setPhone(phone)
                .submit();

        // Проверки
        modal.shouldBeVisible()
                .checkStudentName(firstName, lastName)
                .checkGender("Male")
                .checkPhone(phone);
    }

    @Test
    void negativeRegistrationTest() {
        //Данные
        String lastName = "Sidorov";
        String phone = "1112223344";

        // Действия
        RegistrationResultsModal modal = new RegistrationPage()
                .openPage()
                .setLastName(lastName)
                .setGender("Female")
                .setPhone(phone)
                .submit();

        // Проверки
        modal.shouldNotBeVisible();
    }
}