package Pages;

import Classes.User;
import TestBase.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends PageObject {
    @FindBy(xpath = "//*[@id=\"rcmloginuser\"]")
    private WebElement username;
    @FindBy(xpath = "//*[@id=\"rcmloginpwd\"]")
    private WebElement password;
    @FindBy(xpath = "//*[@id=\"rcmloginsubmit\"]")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"message\"]")
    private WebElement statusMessage;

    public LoginPage(){
        super(DriverFactory.getInstance().getDriver());
        Assert.assertTrue(this.isInitialized());
    }

    public MainPage LoginUser(User user){
        this.EnterCredentials(user);
        Wait<WebDriver> wait = customWait(15, 1000);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("INBOX"));
        return new MainPage();
    }

    public void LoginInvalidUser(User user){
        this.EnterCredentials(user);
        loginButton.click();
        System.out.println(driver.getTitle());
    }

    private void EnterCredentials(User user){
        username.clear();
        username.sendKeys(user.getUsername());
        password.clear();
        password.sendKeys(user.getPassword());
    }

    public boolean isInitialized(){
        Wait<WebDriver> wait = customWait(15,100);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return loginButton.isDisplayed();
    }

    public String GetStatusMessage(){
        Wait<WebDriver> wait = customWait(15,100);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(statusMessage, "Loading...")));
        return statusMessage.getText();
    }
}
