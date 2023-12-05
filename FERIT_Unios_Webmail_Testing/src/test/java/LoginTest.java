import Classes.User;
import Pages.LoginPage;
import Pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends FunctionalTest{
    @Test
    public void Login(){
        LoginPage loginPage = new LoginPage(driver);
        User user = User.GetUser();
        MainPage mainPage = loginPage.LoginUser(user);
        String actualCurrentUser = user.getUsername()+"@student.ferit.hr";
        Assert.assertEquals(mainPage.GetCurrentUser(), actualCurrentUser);
    }
}
