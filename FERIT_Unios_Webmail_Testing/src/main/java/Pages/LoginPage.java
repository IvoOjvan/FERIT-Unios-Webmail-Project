package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
public class LoginPage {

    private static WebElement element = null;
    public static WebElement textbox_username(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"rcmloginuser\"]"));
        return element;
    }

    public static WebElement textbox_password(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"rcmloginpwd\"]"));
        return element;
    }

    public static WebElement button_login(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"rcmloginsubmit\"]"));
        return element;
    }

    public static WebElement link_getSupport(WebDriver driver){
        element = driver.findElement(By.xpath("/html/body/div/div[3]/a"));
        return element;
    }


}
