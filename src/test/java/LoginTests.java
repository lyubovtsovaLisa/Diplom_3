import api.UserApiRequests;
import api.UserInfo;
import api.UserLoginData;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static config.Constants.*;
@Feature("Тесты логина пользователя")
public class LoginTests extends BaseTest {
    private UserApiRequests apiRequests;
    private UserLoginData userLoginData;
    private UserInfo userInfo;
    @Before
    public void localSetUp() {
        apiRequests = new UserApiRequests();
        String uniqueEmail = EMAIL_PREFIX + System.currentTimeMillis() + EMAIL_DOMAIN;
        userInfo= new UserInfo(uniqueEmail,PASSWORD,NAME);
        userLoginData = new UserLoginData(uniqueEmail, PASSWORD);
        apiRequests.createUser(userInfo);
    }
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Успешный переход на страницу входа и вход пользователя через кнопку «Войти в аккаунт» главной странице")
    public void mainPageLoginTest() {
        mainPage.openPage();
        mainPage.clickOnLoginButton();
        loginPage.login(userLoginData.getEmail(),userLoginData.getPassword());
        mainPage.clickOnProfileButton();
        Assert.assertTrue("Страница профиля не открыта, вход не выполнен!",
        mainPage.isRedirectedToProfilePage());

    }
    @Test
    @DisplayName("Вход по кнопке «Личный кабинет»")
    @Description("Успешный переход на страницу входа и вход пользователя по кнопке «Личный кабинет»")
    public void profileButtonLoginTest() {
        mainPage.openPage();
        mainPage.clickOnProfileButton();
        loginPage.login(userLoginData.getEmail(),userLoginData.getPassword());
        mainPage.clickOnProfileButton();
        Assert.assertTrue("Страница профиля не открыта, вход не выполнен!",
                mainPage.isRedirectedToProfilePage());

    }
    @Test
    @DisplayName("Вход на странице регистрации")
    @Description("Успешный переход на страницу входа и вход пользователя по ссылке «Войти» на странице регистрации")
    public void registerPageLoginTest() {
        registerPage.openPage();
        registerPage.clickOnGoToLogin();
        loginPage.login(userLoginData.getEmail(),userLoginData.getPassword());
        mainPage.clickOnProfileButton();
        Assert.assertTrue("Страница профиля не открыта, вход не выполнен!",
                mainPage.isRedirectedToProfilePage());

    }
    @Test
    @DisplayName("Вход на странице восстановления пароля")
    @Description("Успешный переход на страницу входа и вход пользователя по ссылке «Войти» на странице восстановления пароля")
    public void forgotPassLoginTest() {
        forgetPassPage.openPage();
        forgetPassPage.clickOnGoToLogin();
        loginPage.login(userLoginData.getEmail(),userLoginData.getPassword());
        mainPage.clickOnProfileButton();
        Assert.assertTrue("Страница профиля не открыта, вход не выполнен!",
                mainPage.isRedirectedToProfilePage());

    }
    @After
    public void cleanUp() {
        String token = apiRequests.getAccessToken(userLoginData);
        if (token != null) {
            apiRequests.deleteUser(token);
        }
    }
}
