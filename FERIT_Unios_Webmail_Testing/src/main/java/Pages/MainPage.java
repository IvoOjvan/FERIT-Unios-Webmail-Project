package Pages;

import Classes.Email;
import TestBase.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class MainPage extends PageObject{
    @FindBy(xpath = "//*[@id=\"rcmbtn101\"]")
    private WebElement logoutButton;
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/span")
    private WebElement currentUser;
    @FindBy(xpath = "//*[@id=\"rcmbtn107\"]")
    private WebElement composeButton;
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div[1]/ul/li[3]/a")
    private WebElement sentEmailsButton;
    @FindBy(xpath = "//*[@id=\"messagelist\"]")
    private WebElement sentEmailsTable;

    public MainPage(){
        super(DriverFactory.getInstance().getDriver());
        Assert.assertTrue(this.isInitialized());
    }

    public LoginPage Logout(){
        Wait<WebDriver> wait = customWait(15, 1000);
        // iz nekog bozjeg razloga radi s duplim klikom
        //logoutButton.click();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
        logoutButton.click();
        wait.until(ExpectedConditions.invisibilityOf(logoutButton));
        return new LoginPage();
    }

    public String GetCurrentUser(){
        return currentUser.getText();
    }

    public boolean isInitialized(){
        return logoutButton.isDisplayed();
    }

    public ComposeEmailPage ComposeEmail() {
        Wait<WebDriver> wait = customWait(30, 1000);
        wait.until(ExpectedConditions.visibilityOf(composeButton));
        composeButton.click();
        //wait = customWait(30,1000);
        //composeButton.click();
        driver.navigate().to("https://www.etfos.unios.hr/webmail/?_task=mail&_action=compose");
        wait.until(ExpectedConditions.urlContains("compose"));
        return new ComposeEmailPage();
    }

    public void ShowSentEmails(){
        Wait<WebDriver> wait = customWait(15,1000);
        wait.until(ExpectedConditions.elementToBeClickable(sentEmailsButton));
        sentEmailsButton.click();
        sentEmailsButton.click();
    }

    public boolean CheckIfEmailExists(Email email){
        this.ShowSentEmails();

        Wait<WebDriver> wait = customWait(15, 1000);
        wait.until(ExpectedConditions.textToBePresentInElement(sentEmailsTable,sentEmailsTable.getText()));

        List<WebElement> rows = sentEmailsTable.findElements(By.tagName("tr"));

        for(WebElement rowElement : rows){
            List<WebElement> cells = rowElement.findElements(By.tagName("td"));

            for(WebElement cellElement : cells){
                String cellData = cellElement.getText();

                // If there is a row and that row contains mail subject then sent mail exists
                if(cellData.equals(email.GetSubject())){
                    return true;
                }
            }
        }
        return false;
    }
}
