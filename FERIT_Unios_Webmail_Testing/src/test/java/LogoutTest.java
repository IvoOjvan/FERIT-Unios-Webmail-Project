import Classes.User;
import Pages.LoginPage;
import Pages.MainPage;
import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTest extends TestBase {
    @Test
    public void Logout(){
        MainPage mainPage = new LoginPage().LoginUser(User.GetUser());

        String expectedMessage = "You have successfully terminated the session. Good bye!";
        LoginPage loginPage = mainPage.Logout();
        String actualMessage = loginPage.GetStatusMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
