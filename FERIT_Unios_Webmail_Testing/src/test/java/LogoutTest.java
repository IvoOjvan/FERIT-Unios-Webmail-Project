import Classes.User;
import Pages.LoginPage;
import Pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest {
    public WebDriver driver;
    public String testURL = "https://www.etfos.unios.hr/webmail/";

    @BeforeMethod
    void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.navigate().to(testURL);
    }

    @Test
    void ValidLogoutTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        Thread.sleep(3000);

        MainPage mainPage = loginPage.LoginValidUser(User.GetUser());

        Thread.sleep(3000);

        loginPage = mainPage.LogoutValidUser();

        Assert.assertEquals(loginPage.GetPageTitle(), "Student.FERIT Webmail :: Welcome to Student.FERIT Webmail");
    }


    @AfterMethod
    void teardown(){
        driver.quit();
    }
}
