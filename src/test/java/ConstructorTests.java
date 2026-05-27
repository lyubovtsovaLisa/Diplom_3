import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

@Feature("Тесты конструктора")
public class ConstructorTests extends BaseTest{
    @Test
    @DisplayName("Переход на вкладу Соусы")
    @Description("Успешный переход на вкладку Соусы, она становится активной")
    public void activateSauceTabTest() {
        mainPage.openPage();
        mainPage.clickOnSauceTab();
        Assert.assertTrue("Вкладка Соусы не активна!",
                mainPage.isSauceTabActive());

    }
    @Test
    @DisplayName("Переход на вкладу Начинки")
    @Description("Успешный переход на вкладку Начинки, она становится активной")
    public void activateFillingTabTest() {
        mainPage.openPage();
        mainPage.clickOnFillingTab();
        Assert.assertTrue("Страница переход на вкладку Начинки не выполнен!",
                mainPage.isFillingTabActive());

    }
    @Test
    @DisplayName("Переход на вкладу Булки")
    @Description("Успешный переход на вкладку Булки, она становится активной")
    public void activateBunTabTest() {
        mainPage.openPage();
        mainPage.clickOnFillingTab();
        mainPage.clickOnBunTab();
        Assert.assertTrue("Страница переход на вкладку Булки не выполнен!",
                mainPage.isBunTabActive());

    }

}
