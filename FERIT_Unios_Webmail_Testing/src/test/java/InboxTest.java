import Classes.Email;
import Classes.User;
import Pages.ComposeEmailPage;
import Pages.LoginPage;
import Pages.MainPage;
import TestBase.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class InboxTest extends TestBase {
    /*
    @Test
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
        Assert.assertNotNull(mainPage);

        Boolean expectedResult = true;
        Boolean actualResult = mainPage.CheckIfEmailExists(email);
        Assert.assertEquals(actualResult, expectedResult);
    }*/

    @Test
    public void DeleteMail(){
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.LoginUser(User.GetUser());
        ComposeEmailPage composeEmailPage = mainPage.ComposeEmail();

        Email email = new Email(
                "iojvan@etfos.hr",
                "Mail to be deleted",
                "This mail should be deleted."
        );

        mainPage = composeEmailPage.SendEmail(email);
        mainPage.DeleteMail(email);

        String expectedMessage = "Message(s) deleted successfully.";
        String actualMessage = mainPage.GetStatusMessage();

        Assert.assertEquals(actualMessage, expectedMessage);
    }
}
