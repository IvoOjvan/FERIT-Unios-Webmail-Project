package Pages;

import Classes.User;
import TestBase.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends PageObject {
    @FindBy(id = "rcmloginuser")
    private WebElement username;
    @FindBy(id = "rcmloginpwd")
    private WebElement password;
    @FindBy(id = "rcmloginsubmit")
    private WebElement loginButton;
    @FindBy(id = "message")
    private WebElement statusMessage;

    public LoginPage(){
        super(DriverFactory.getInstance().getDriver());
        Assert.assertTrue(this.isInitialized());
    }

    public MainPage LoginUser(User user){
        this.EnterCredentials(user);
        customWait(15, 1000)
                .until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        customWait(15,1000)
                .until(ExpectedConditions.urlContains("INBOX"));
        return new MainPage();
    }

    public void LoginInvalidUser(User user){
        this.EnterCredentials(user);
        loginButton.click();
    }

    private void EnterCredentials(User user){
        username.clear();
        username.sendKeys(user.getUsername());
        password.clear();
        password.sendKeys(user.getPassword());
    }

    public boolean isInitialized(){
        customWait(15,1000)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        return loginButton.isEnabled();
    }

    public String GetStatusMessage(){
        customWait(15,1000)
                .until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(statusMessage, "Loading...")));
        return statusMessage.getText();
    }
}
