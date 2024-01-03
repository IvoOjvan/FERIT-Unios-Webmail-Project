# FERIT-Unios-Webmail-Project

Project that provides automated functional tests, such as login and logout, using selenium
webdriver, Java programing language and maven architecture.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Pages](#pages)
5. [Tests](#tests)
6. [testng.xml](#testng)
7. [WebDriverManager](#webDriverManager)
8. [BrowserFactory](#browserFactory)
9. [DriverFactory](#driverFactory)

<a name="prerequisites"></a>
## Prerequisites
  1. Java (JDK)
  2. Web browser, Firefox and Chrome, Edge (Optional)
  3. IntelliJ Community Edition (Optional)
  4. Windows operating system

<a name="installation"></a>
# Installation
### 1. Clone the repository

```
git clone https://github.com/IvoOjvan/FERIT-Unios-Webmail-Project.git
```
### 2. Reload maven project
  1. Right click on pom.xml
  2. Hover Maven
  2. Click "Reload project"

### 3. Download Allure binnaries

Allure binnaries can be downloaded at [Allure binnaries](https://github.com/allure-framework/allure2/releases/tag/2.25.0/)

### 4. Add Allure to IDE Path Variables

e.g. allure-2.25.0\allure-2.25.0\bin

<a name="usage"></a>
# Usage
### 1. Enter project folder
```
cd FERIT-Unios-Webmail-Testing
```
### 2. Run tests using testng.xml
In terminal enter this line (run with ctrl + enter):

```
mvn test
```
### 3. See test results
In terminal enter this line:
 ```
 allure serve allure-results
 ```

<a name="pages"></a>
# Pages
Best practice states that before writing test you should make classes representing each page of
web application this approach is called Page Object Model

### PageObject class
This class contains of WebDriver instance and constructor which sets value to driver instance using  
dependecy injection and initializig element using PageFactory

```java
public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
```

### Actual Page Object
Actual page object should extend PageObject class and in this class all WebElements such as
buttons, textboxs, images, ... should be defind using @FindBy() notation which can take xpath, name,
id or something else as parameter.

e.g.
```java
public class ReceiptPage extends PageObject {
    @FindBy(tagName = "h1")
    private WebElement header;

    public ReceiptPage(WebDriver driver) {
        super(driver);
    }
}
```

<a name="tests"></a>
# Tests
### TestBase class
This class is base for all test classes, it contains BrowserFactory class instance which will
be used to launch specific browser specified in testng.xml file. It also contains @BeforeMethod, which
means that method with this annotation will run before every test method, and @AfterMethod annotation.

```java
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
```

### Actual test class
Test class should extend FunctionalTest class and contain only test method
marked with @Test

```java
public class SignUpFormTest extends TestBase {

	@Test
	public void signUp(){
		test code...
	}	
}
```
<a name="testng"></a>
# testng.xml
An XML file that contains all the information how and which tests will be run when "mvn test"
command is run.
eg.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="CrossBrowserTesting" parallel="methods">
    <test name="ChromeTest">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="LoginTest"/>
            <class name="LogoutTest"/>
            <class name="InboxTest"/>
        </classes>
    </test>
    <test name="FirefoxTest">
        <parameter name="browser" value="firefox"/>
        <classes>
            <class name="LoginTest"/>
            <class name="LogoutTest"/>
            <class name="InboxTest"/>
        </classes>
    </test>
</suite>
```

<a name="webDriverManager"></a>
# WebDriverManager
Very useful way to ensure that you browser driver will be the correct version as installed on
another device.

### Installation
Go to [MavenRepository](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager) and chose which
version you need and add the dependency to pom.xml file and reload maven project.

### Use
Usage is very simple as shown in the example, setup method will download correct browser driver version and
all that is left to do is initialize driver variable.
eg.
```java
WebDriverManager.firefoxdriver().setup();
driver = new FirefoxDriver(options);
```

<a name="browserFactory"></a>
# BrowserFactory
Purpose of BrowserFactory is to make running parallel tests easier, in the testng.xml file in the parameter tag 
is passed the name of the browser eg. "firefox" and BrowserFactory using createBrowserInstance method, will for 
that thread run new browser instance in private mode of that type using WebDriverManager.

```java
public class BrowserFactory {
    public WebDriver createBrowserInstance(String browser){
        WebDriver driver = null;
        if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
        }else if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }else if(browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        return driver;
    }
```
<a name="driverFactory"></a>
# DriverFactory
DriverFactory is responsible for managing that in a single thread there is only 1 driver instance working using
Singleton pattern.

```java
public class DriverFactory {
    private static final DriverFactory instance = new DriverFactory();

    private DriverFactory(){}

    public static DriverFactory getInstance(){
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
```

