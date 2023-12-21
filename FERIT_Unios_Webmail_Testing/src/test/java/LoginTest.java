import Classes.User;
import Pages.LoginPage;
import Pages.MainPage;
import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends TestBase {

    @Test
    public void LoginValidCredentialsUser(){
        LoginPage loginPage = new LoginPage();
        User user = User.GetUser();
        MainPage mainPage = loginPage.LoginUser(user);
        String actualCurrentUser = user.getUsername()+"@student.ferit.hr";
        Assert.assertEquals(mainPage.GetCurrentUser(), actualCurrentUser);
    }

    @Test
    public void LoginIncorrectCredentialsUser(){
        String username = "IncorrectUsername";
        String password = "12345678";
        User user = new User(username,password);

        LoginPage loginPage = new LoginPage();
        loginPage.LoginInvalidUser(user);

        String expectedStatus = "Login failed.";
        Assert.assertEquals(loginPage.GetStatusMessage(), expectedStatus);
    }
}
