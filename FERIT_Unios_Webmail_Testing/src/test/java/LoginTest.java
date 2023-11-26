import Classes.User;
import Pages.LoginPage;
import Pages.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.reporters.jq.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    public WebDriver driver;
    public String testURL = "https://www.etfos.unios.hr/webmail/";

    @BeforeMethod
    void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.navigate().to(testURL);
    }

    @Test
    void ValidLoginTest(){
        User user = User.GetUser();

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.LoginValidUser(user);

        Assert.assertEquals(mainPage.GetCurrentUser(), user.getUsername()+"@student.ferit.hr");
    }


    @AfterMethod
    void teardown(){
        driver.quit();
    }
}
