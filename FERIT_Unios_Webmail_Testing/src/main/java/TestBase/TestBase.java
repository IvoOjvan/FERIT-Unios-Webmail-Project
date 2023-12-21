package TestBase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class TestBase {
    BrowserFactory browserFactory = new BrowserFactory();

    @BeforeMethod
    @Parameters("browser")
    public void LaunchBrowser(String browser){
        WebDriver driverInstance = browserFactory.createBrowserInstance(browser);
        DriverFactory.getInstance().setDriver(driverInstance);

        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.etfos.unios.hr/webmail/");
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.getInstance().closeBrowser();
    }
}
