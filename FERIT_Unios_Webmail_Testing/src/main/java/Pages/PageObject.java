package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Wait<WebDriver> customWait(long timeoutInSeconds, long pollingInMillis){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);
    }
}
