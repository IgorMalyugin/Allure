package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class MetodTest {
    @Step("открываем главную страницу")
    public MetodTest openPage() {
        open("https://github.com");
        return this;
    }

    @Step("ищем репозиторий {repo}")
    public MetodTest searchForRepository(String repo) {
        $(".header-search-input").setValue(repo).pressEnter();
        return this;
    }

    @Step("кликаем по ссылке репозитория ")
    public MetodTest clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("кликаем таб issues")
    public MetodTest openIssuesTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("проверяем наличие Issue с номером {issue}")
    public MetodTest shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
