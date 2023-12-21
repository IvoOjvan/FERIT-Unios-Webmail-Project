import Classes.Email;
import Classes.User;
import Pages.ComposeEmailPage;
import Pages.LoginPage;
import Pages.MainPage;
import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;


public class InboxTest extends TestBase {

    @Test(priority = 1)
    public void SendValidEmail(){
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.LoginUser(User.GetUser());
        ComposeEmailPage composeEmailPage = mainPage.ComposeEmail();

        Email email = new Email(
                "iojvan@etfos.hr",
                "Test mail subject",
                "Test mail message"
        );

        mainPage = composeEmailPage.SendEmail(email);
        Assert.assertTrue(mainPage.CheckIfEmailExists(email));
    }


    @Test(priority = 2)
    public void SendEmptyReceiverEmail(){
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.LoginUser(User.GetUser());
        ComposeEmailPage composeEmailPage = mainPage.ComposeEmail();

        Email email = new Email(
                "",
                "Empty receiver subject",
                "Empty receiver message"
        );

        mainPage = composeEmailPage.SendInvalidEmail(email);

        Email alertEmail = new Email(
                "iojvan@etfos.hr",
                "Alert - Receiver added additionally!",
                "Empty receiver message"
        );
        Assert.assertTrue(mainPage.CheckIfEmailExists(alertEmail));
    }
}
