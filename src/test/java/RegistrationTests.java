import api.UserApiRequests;
import api.UserLoginData;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static config.Constants.*;


@Feature("Регистрация пользователя")
public class RegistrationTests extends BaseTest {
        private UserApiRequests apiRequests;
        private UserLoginData userLoginData;

        @Before
        public void localSetUp() {
            apiRequests = new UserApiRequests();
            String uniqueEmail = EMAIL_PREFIX + System.currentTimeMillis() + EMAIL_DOMAIN;
            userLoginData = new UserLoginData(uniqueEmail, PASSWORD);
        }

        @Test
        @DisplayName("Успешная регистрация пользователя")
        @Description("Заполнение всех валидных полей приводит к успешному созданию аккаунта и редиректу на страницу логина")
        public void successfulRegistrationTest() {
            registerPage.openPage();
            registerPage.register(NAME, userLoginData.getEmail(), userLoginData.getPassword());
            Assert.assertTrue("Пользователь не перенаправлен на страницу логина!",
                    registerPage.isRedirectedToLoginPage());

        }

        @Test
        @DisplayName("Ошибка регистрации при некорректном пароле")
        @Description("Регистрация с паролем менее 6 символов показывает ошибку на форме")
        public void registrationWithShortPasswordErrorTest() {
            registerPage.openPage();
            registerPage.register(NAME, userLoginData.getEmail(), INVALID_PASSWORD);
            Assert.assertTrue("Сообщение об ошибке некорректного пароля не отобразилось!", registerPage.isPasswordErrorDisplayed());
        }

        @After
        public void cleanUp() {
            String token = apiRequests.getAccessToken(userLoginData);
            if (token != null) {
                apiRequests.deleteUser(token);
            }
        }
    }

