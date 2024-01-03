package Pages;

import Classes.Email;
import TestBase.DriverFactory;
import com.beust.ah.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;

public class ComposeEmailPage extends PageObject{
    @FindBy(id = "rcmbtn106")
    private WebElement cancelButton;
    @FindBy(id = "rcmbtn107")
    private WebElement sendEmailButton;
    @FindBy(id = "rcmbtn108")
    private WebElement saveEmailButton;
    @FindBy(id = "_to")
    private WebElement receiverTextBox;
    @FindBy(id = "compose-subject")
    private WebElement subjectTextBox;
    @FindBy(id = "composebody")
    private WebElement emailBodyTextBox;
    @FindBy(id = "messagestack")
    private WebElement statusMessage;

    public ComposeEmailPage(){
        super(DriverFactory.getInstance().getDriver());
        Assert.assertTrue(this.IsInitialized());
    }

    public boolean IsInitialized(){
        customWait(15, 1000)
                .until(ExpectedConditions.visibilityOf(receiverTextBox));
        return receiverTextBox.isDisplayed();
    }

    public String GetStatusMessage(){
       customWait(10,1000)
               .until(ExpectedConditions.textToBePresentInElement(statusMessage, statusMessage.getText()));
       customWait(15,1000)
                .until(ExpectedConditions.attributeContains(statusMessage, "style", "color:#00e05a"));
        return statusMessage.getText();
    }

    public MainPage SendEmail(Email email){
        FillInEmail(email);
        customWait(15, 1000)
                .until(ExpectedConditions.elementToBeClickable(sendEmailButton));
        sendEmailButton.click();
        return new MainPage();
    }

    private void FillInEmail(Email email){
        customWait(5,1000)
                .until(ExpectedConditions.visibilityOf(receiverTextBox));
        receiverTextBox.sendKeys(email.GetReceiver());
        subjectTextBox.sendKeys(email.GetSubject());
        emailBodyTextBox.sendKeys(email.GetMessage());
    }
}
