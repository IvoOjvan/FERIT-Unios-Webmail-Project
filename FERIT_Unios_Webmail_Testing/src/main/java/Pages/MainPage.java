package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    protected WebDriver driver;

    //region Elements
    private By usernameSpan = By.xpath("/html/body/div[1]/div[1]/div[2]/span");
    private By logoutButton = By.xpath("//*[@id=\"rcmbtn101\"]");
    //endregion

    public MainPage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().equals("Student.FERIT Webmail :: Inbox")){
            throw new IllegalStateException("This is not main page," +
                    "current page is: " + driver.getCurrentUrl());
        }
    }

    public String GetCurrentUser(){
        return driver.findElement(usernameSpan).getText();
    }

    public LoginPage LogoutValidUser(){
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }


}
