package Pages;

import Classes.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends PageObject {
    @FindBy(xpath = "//*[@id=\"rcmloginuser\"]")
    private WebElement username;

    @FindBy(xpath = "//*[@id=\"rcmloginpwd\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"rcmloginsubmit\"]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
        Assert.assertTrue(this.isInitialized());
    }

    public MainPage LoginUser(User user){
        username.sendKeys(user.getUsername());
        password.sendKeys(user.getPassword());
        loginButton.click();
        return new MainPage(driver);
    }

    public String GetPageTitle(){
        return driver.getTitle();
    }

    public boolean isInitialized(){
        return loginButton.isDisplayed();
    }
}
