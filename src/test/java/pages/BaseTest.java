package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    void cleanUpAfterEachTest() {
        attachScreenshot();
        attachPageSource();
        attachConsoleLogs();
        attachVideo();

        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @AfterAll
    static void globalTearDown() {
        closeWebDriver();
    }

    // 1. Скриншот (через аннотацию @Attachment)
    @io.qameta.allure.Attachment(value = "Скриншот", type = "image/png")
    private byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    // 2. Page Source
    @io.qameta.allure.Attachment(value = "Page Source", type = "text/html")
    private String attachPageSource() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }

    // 3. Логи консоли
    @io.qameta.allure.Attachment(value = "Console Logs", type = "text/plain")
    private String attachConsoleLogs() {
        LogEntries logEntries = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
        StringBuilder logs = new StringBuilder();
        for (LogEntry entry : logEntries) {
            logs.append(entry.getMessage()).append("\n");
        }
        return logs.toString();
    }

    // 4. Видео (через HTML-плеер)
    @io.qameta.allure.Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    private String attachVideo() {
        try {
            String sessionId = ((org.openqa.selenium.remote.RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
            String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";
            return "<html><body><video width='100%' height='100%' controls autoplay><source src='" + videoUrl + "' type='video/mp4'></video></body></html>";
        } catch (Exception e) {
            return "Video not available: " + e.getMessage();
        }
    }
}