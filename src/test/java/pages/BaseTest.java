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
        takeScreenshot();
        getPageSource();
        getConsoleLogs();
        addVideo();

        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWindow();
    }

    @AfterAll
    static void globalTearDown() {
        closeWebDriver();
    }

    private void takeScreenshot() {
        byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Скриншот", "image/png", new ByteArrayInputStream(screenshot), "png");
    }

    private void getPageSource() {
        String pageSource = WebDriverRunner.getWebDriver().getPageSource();
        Allure.addAttachment("Page Source", "text/html", pageSource, "html");
    }

    private void getConsoleLogs() {
        LogEntries logEntries = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
        StringBuilder logs = new StringBuilder();
        for (LogEntry entry : logEntries) {
            logs.append(entry.getMessage()).append("\n");
        }
        Allure.addAttachment("Console Logs", "text/plain", logs.toString(), "txt");
    }

    private void addVideo() {
        try {
            // Приводим driver к RemoteWebDriver, чтобы вызвать getSessionId()
            String sessionId = ((org.openqa.selenium.remote.RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
            String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";
            String videoHtml = "<html><body><video width='100%' height='100%' controls autoplay><source src='" + videoUrl + "' type='video/mp4'></video></body></html>";
            Allure.addAttachment("Video", "text/html", videoHtml, "html");
        } catch (Exception e) {
            Allure.addAttachment("Video", "text/plain", "Video not available: " + e.getMessage(), "txt");
        }
    }
}