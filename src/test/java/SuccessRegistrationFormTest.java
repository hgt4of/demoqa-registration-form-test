import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.BaseTest;
import pages.RegistrationPage;
import pages.RegistrationResultsModal;

public class SuccessRegistrationFormTest extends BaseTest {

    @Test
    void fullRegistrationTest() {

        // Данные
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String address = faker.address().fullAddress();
        String subject = "Biology";
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
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().subscriberNumber(10);

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
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().subscriberNumber(10);

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

