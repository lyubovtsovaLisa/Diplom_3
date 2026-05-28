package com.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.praktikum.config.PageConfig.MAIN_PAGE_URL;
import static com.praktikum.config.PageConfig.PROFILE_PAGE_URL;


public class MainPage {
    private WebDriver driver;
    private By goToLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private By bunTab = By.xpath("//span[text()='Булки']/..");
    private By sauceTab = By.xpath("//span[text()='Соусы']/..");
    private By fillingTab = By.xpath("//span[text()='Начинки']/..");

    public MainPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Открыть главную страницу")
    public void openPage() {
        driver.get(MAIN_PAGE_URL);
    }
    @Step("Нажать на кнопку «Войти в аккаунт»")
    public void clickOnLoginButton(){
        driver.findElement(goToLoginButton).click();
    }
    @Step("Нажать на кнопку «Личный кабинет»")
    public void clickOnProfileButton(){
        driver.findElement(profileButton).click();
    }

    public boolean isRedirectedToProfilePage() {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlToBe(PROFILE_PAGE_URL));
    }

    @Step("Кликнуть на вкладку Булки")
    public void clickOnBunTab() {
        driver.findElement(bunTab).click();
    }

    @Step("Кликнуть на вкладку Соусы")
    public void clickOnSauceTab() {
        driver.findElement(sauceTab).click();
    }

    @Step("Кликнуть на вкладку Начинки")
    public void clickOnFillingTab() {
        driver.findElement(fillingTab).click();
    }
    public boolean isBunTabActive() {
        return new WebDriverWait(driver, 4)
                .until(ExpectedConditions.attributeContains(bunTab, "class", "tab_type_current"));
    }

    public boolean isSauceTabActive() {
        return new WebDriverWait(driver, 4)
                .until(ExpectedConditions.attributeContains(sauceTab, "class", "tab_type_current"));
    }

    public boolean isFillingTabActive() {
        return new WebDriverWait(driver, 4)
                .until(ExpectedConditions.attributeContains(fillingTab, "class", "tab_type_current"));
    }
}
