import Classes.User;
import Pages.LoginPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

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
        User user = getUser();

        LoginPage.textbox_username(driver).sendKeys(user.getUsername());
        LoginPage.textbox_password(driver).sendKeys(user.getPassword());

        LoginPage.button_login(driver).click();

        WebElement userMail = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/span"));

        Assert.assertEquals(userMail.getText(), user.getUsername()+"@student.ferit.hr");
    }

    @AfterMethod
    void teardown(){
        driver.quit();
    }

    private User getUser() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("./user.json");
        User user;
        try {
            user = mapper.readValue(file, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
