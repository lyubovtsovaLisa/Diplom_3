package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static config.PageConfig.LOGIN_PAGE_URL;

public class LoginPage { private WebDriver driver;
    private By signInButton = By.xpath("//button[text()='Войти']");
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Открыть страницу логина")
    public void openPage() {
        driver.get(LOGIN_PAGE_URL);
    }
    @Step("Ввести email")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Ввести пароль")
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Нажать на кнопку Войти")
    public void clickOnSignIn(){
        driver.findElement(signInButton).click();
    }
    @Step("Выполнить вход в аккаунт")
    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickOnSignIn();
    }
}
