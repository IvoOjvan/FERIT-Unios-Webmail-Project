package Pages;

import Classes.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
public class LoginPage {
    protected WebDriver driver;

    //region Elements
    private By usernameTextbox = By.xpath("//*[@id=\"rcmloginuser\"]");
    private By passwordTextbox = By.xpath("//*[@id=\"rcmloginpwd\"]");
    private By loginButton = By.xpath("//*[@id=\"rcmloginsubmit\"]");
    //endregion

    public LoginPage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().equals("Student.FERIT Webmail :: Welcome to Student.FERIT Webmail")){
            throw new IllegalStateException("This is not Sign In Page," +
                    "current page is: " + driver.getCurrentUrl());
        }
    }

    public MainPage LoginValidUser(User user){
        driver.findElement(usernameTextbox).sendKeys(user.getUsername());
        driver.findElement(passwordTextbox).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

    public String GetPageTitle(){
        return driver.getTitle();
    }
}
