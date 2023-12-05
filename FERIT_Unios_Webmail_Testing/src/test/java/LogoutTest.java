import Classes.User;
import Pages.LoginPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class LogoutTest extends FunctionalTest{
    private MainPage mainPage;

    @BeforeMethod
    void set(){
        User user = User.GetUser();
        LoginPage loginPage = new LoginPage(driver);
        mainPage = loginPage.LoginUser(user);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void Logout() throws InterruptedException {
        String expectedTitle = "Student.FERIT Webmail :: Welcome to Student.FERIT Webmail";
        LoginPage loginPage = mainPage.Logout();
        System.out.println(driver.getTitle());
        String actualTitle = loginPage.GetPageTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
