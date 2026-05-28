package com.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.praktikum.config.PageConfig.LOGIN_PAGE_URL;
import static com.praktikum.config.PageConfig.REGISTER_PAGE_URL;

public class RegisterPage {
    private WebDriver driver;
    private By goToLoginLink= By.xpath("//a[contains(text(),'Войти')]");
    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By passwordError = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Открыть страницу регистрации")
    public void openPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Нажать на ссылку Войти")
    public void clickOnGoToLogin(){
        driver.findElement(goToLoginLink).click();
    }
    @Step("Ввести Имя")
    public void setName(String name){
        driver.findElement(nameField).sendKeys(name);
    }
    @Step("Ввести email")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Ввести пароль")
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Нажать на кнопку Зарегистрироваться")
    public void clickOnRegisterButton(){
        driver.findElement(registrationButton).click();
    }

    public boolean isPasswordErrorDisplayed() {
        return driver.findElement(passwordError).isDisplayed();
    }

    public boolean isRedirectedToLoginPage() {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
    }
    @Step("Зарегистрировать пользователя")
    public void register(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnRegisterButton();
    }
}
