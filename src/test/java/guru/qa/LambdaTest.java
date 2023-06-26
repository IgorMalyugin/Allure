package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaTest {

    private static final String repository = "IgorMalyugin/Selenide";
    private static final int issue = 1;

    @Test
    public void testIssueSearchLambda() {


        SelenideLogger.addListener("allure", new AllureSelenide());

        step("открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("ищем репозиторий", () -> {
            $(".header-search-input").setValue(repository).pressEnter();
        });

        step("кликаем по ссылке репозитория " + issue, () -> {
            $(linkText(repository)).click();
        });

        step("кликаем таб issues", () -> {
            $("#issues-tab").click();
        });

        step("проверяем наличие Issue с номером " + issue, () -> {
            $(withText("#" + issue)).should(Condition.exist);
        });

        attachment("Source", webdriver().driver().source());


    }

    @Test
    public void testAnnotatedStep() {
        MetodTest metodTest = new MetodTest();

        metodTest.openPage()
                .searchForRepository(repository)
                .clickOnRepositoryLink(repository)
                .openIssuesTab()
                .shouldSeeIssueWithNumber(issue);
        metodTest.takeScreenshot();

    }
}
