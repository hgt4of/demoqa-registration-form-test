package pages;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    protected static Faker faker = new Faker();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void cleanUpAfterEachTest() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @AfterAll
    static void globalTearDown() {
        closeWebDriver();
    }
}