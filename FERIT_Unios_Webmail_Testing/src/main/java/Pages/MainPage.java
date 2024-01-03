package Pages;

import Classes.Email;
import TestBase.DriverFactory;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainPage extends PageObject{
    @FindBy(id = "rcmbtn101")
    private WebElement logoutButton;
    @FindBy(id = "username")
    private WebElement currentUser;
    @FindBy(id = "rcmbtn107")
    private WebElement composeButton;
    @FindBy(id = "rcmliU2VudA")
    private WebElement sentEmailsButton;
    @FindBy(id = "messagelist")
    private WebElement sentEmailsTable;
    @FindBy(id = "rcmbtn111")
    private WebElement deleteMailButton;
    @FindBy(id = "messagestack")
    private WebElement statusMessage;

    public MainPage(){
        super(DriverFactory.getInstance().getDriver());
        Assert.assertTrue(this.isInitialized());
    }

    public LoginPage Logout(){
        customWait(15, 1000)
                .until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        logoutButton.click();
        return new LoginPage();
    }

    public String GetCurrentUser(){
        return currentUser.getText();
    }

    public boolean isInitialized(){
        customWait(15,1000)
                .until(ExpectedConditions.elementToBeClickable(logoutButton));
        return logoutButton.isEnabled();
    }

    public ComposeEmailPage ComposeEmail() {
        customWait(10, 1000)
                .until(ExpectedConditions.visibilityOf(composeButton));

        Actions action = new Actions(driver);
        action.moveToElement(composeButton).click().perform();

        return new ComposeEmailPage();
    }

    public void DeleteMail(Email email){
        if(CheckIfEmailExists(email)){
            WebElement emailRow = getEmailRow(email);
            Actions action = new Actions(driver);
            if(emailRow != null)
                //emailRow.click();
                action.moveToElement(emailRow).click().perform();
            customWait(10,1000)
                    .until(ExpectedConditions.elementToBeClickable(deleteMailButton));
            action.moveToElement(deleteMailButton).click().perform();
        }
    }

    public String GetStatusMessage(){
        customWait(15,1000)
                .until(ExpectedConditions.not(
                        ExpectedConditions.textToBePresentInElement(statusMessage, statusMessage.getText())));
        return statusMessage.getText();
    }

    public WebElement getEmailRow(Email email){
        customWait(50, 1000)
                .until(ExpectedConditions.textToBePresentInElement(sentEmailsTable, sentEmailsTable.getText()));
        List<WebElement> rows = sentEmailsTable.findElements(By.tagName("tr"));
        for(WebElement rowElement : rows){
            List<WebElement> cells = rowElement.findElements(By.tagName("td"));
            for(WebElement cellElement : cells){
                String cellData = cellElement.getText();
                if(cellData.equals(email.GetSubject()))
                    return rowElement;
            }
        }
        return null;
    }

    public boolean CheckIfEmailExists(Email email){
        customWait(60, 5000)
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.textToBePresentInElement(sentEmailsTable, email.GetSubject())));
        return getEmailRow(email) != null;
    }
}
