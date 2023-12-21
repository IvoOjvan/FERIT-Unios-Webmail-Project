package TestBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static DriverFactory instance = new DriverFactory();
    private DriverFactory(){}
    public static DriverFactory getInstance(){
        //if(instance == null)
            //instance = new DriverFactory();
        return instance;
    }
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver(){
        return this.driver.get();
    }
    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }
    public void closeBrowser(){
        driver.get().close();
        driver.remove();
    }
}
