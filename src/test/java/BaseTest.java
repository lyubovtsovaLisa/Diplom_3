
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.ForgetPassPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;

public class BaseTest {
    public WebDriver driver;
    RegisterPage registerPage;
    MainPage mainPage;
    LoginPage loginPage;
    ForgetPassPage forgetPassPage;
    @Before
    public void startBrowser(){
        RestAssured.replaceFiltersWith(new AllureRestAssured());
        String browser = System.getProperty("browser","chrome");
        if (browser.equals("chrome")){
            startBrowserChrome();
        } else if (browser.equals("yandex")) {
            startBrowserYandex();
        }
    }
    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
       initPages();
    }
    public void startBrowserYandex() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver","C:/Users/lyubo/Downloads/yandexdriver-26.4.3.894-win64/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
       initPages();
    }

    private void initPages() {
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        forgetPassPage = new ForgetPassPage(driver);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
