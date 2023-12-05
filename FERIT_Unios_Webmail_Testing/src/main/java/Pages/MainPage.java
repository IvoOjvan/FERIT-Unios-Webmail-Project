package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import java.time.Duration;

public class MainPage extends PageObject{
    @FindBy(xpath = "//*[@id=\"rcmbtn101\"]")
    private WebElement logoutButton;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/span")
    private WebElement currentUser;


    public MainPage(WebDriver driver){
        super(driver);
        Assert.assertTrue(this.isInitialized());
    }

    public LoginPage Logout(){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200));
        // iz nekog bozjeg razloga radi s duplim klikom
        // moguce zbog implementacije same stranice
        logoutButton.click();
        logoutButton.click();
        wait.until(ExpectedConditions.titleIs("Student.FERIT Webmail :: Welcome to Student.FERIT Webmail"));
        return new LoginPage(driver);
    }

    public String GetCurrentUser(){
        return currentUser.getText();
    }

    public boolean isInitialized(){
        return logoutButton.isDisplayed();
    }
}
