package com.praktikum.page.object;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.praktikum.config.PageConfig.FORGOT_PASS_PAGE_URL;

public class ForgetPassPage {
    private WebDriver driver;
    private By goToLoginLink= By.xpath("//a[contains(text(),'Войти')]");

    public ForgetPassPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Открыть страницу профиля")
    public void openPage() {
        driver.get(FORGOT_PASS_PAGE_URL);
    }

    @Step("Нажать на ссылку Войти")
    public void clickOnGoToLogin(){
        driver.findElement(goToLoginLink).click();
    }
}
