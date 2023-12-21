package Pages;

import Classes.Email;
import TestBase.DriverFactory;
import com.beust.ah.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;

public class ComposeEmailPage extends PageObject{
    @FindBy(xpath = "//*[@id=\"rcmbtn106\"]")
    private WebElement cancelButton;
    @FindBy(xpath = "//*[@id=\"rcmbtn107\"]")
    private WebElement sendEmailButton;
    @FindBy(xpath = "//*[@id=\"rcmbtn108\"]")
    private WebElement saveEmailButton;
    @FindBy(xpath = "//*[@id=\"_to\"]")
    private WebElement receiverTextBox;
    @FindBy(xpath = "//*[@id=\"compose-subject\"]")
    private WebElement subjectTextBox;
    @FindBy(xpath = "//*[@id=\"composebody\"]")
    private WebElement emailBodyTextBox;

    public ComposeEmailPage(){
        super(DriverFactory.getInstance().getDriver());
        Assert.assertTrue(this.IsInitialized());
    }

    public boolean IsInitialized(){
        return cancelButton.isDisplayed();
    }

    public MainPage SendEmail(Email email){
        FillInEmail(email);
        Wait<WebDriver> wait = customWait(15, 1000);
        sendEmailButton.click();
        wait.until(ExpectedConditions.titleContains("Inbox"));
        return new MainPage();
    }

    public MainPage SendInvalidEmail(Email email){
        FillInEmail(email);
        Wait<WebDriver> wait = customWait(15, 1000);
        sendEmailButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        receiverTextBox.sendKeys("iojvan@etfos.hr");
        subjectTextBox.clear();
        subjectTextBox.sendKeys("Alert - Receiver added additionally!");
        emailBodyTextBox.sendKeys(email.GetMessage());
        sendEmailButton.click();
        return new MainPage();
    }

    private void FillInEmail(Email email){
        receiverTextBox.sendKeys(email.GetReceiver());
        subjectTextBox.sendKeys(email.GetSubject());
        emailBodyTextBox.sendKeys(email.GetMessage());
    }
}
