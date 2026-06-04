package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;                    // ← импорт из helpers
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        // Читаем параметры из Jenkins
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "100.0");
        String screenResolution = System.getProperty("screenResolution", "1920x1080");
        String selenoidUrl = System.getProperty("selenoidUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        // Применяем настройки
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = screenResolution;
        Configuration.remote = selenoidUrl;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        // Allure слушатель
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        // Выводим параметры в лог
        System.out.println("=========================================");
        System.out.println("=== Jenkins Parameters Applied ===");
        System.out.println("Browser: " + browser);
        System.out.println("Browser Version: " + browserVersion);
        System.out.println("Screen Resolution: " + screenResolution);
        System.out.println("Selenoid URL: " + selenoidUrl);
        System.out.println("=========================================");
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @AfterAll
    static void globalTearDown() {
        closeWebDriver();
    }
}