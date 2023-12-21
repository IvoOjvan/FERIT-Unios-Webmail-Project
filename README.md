# FERIT-Unios-Webmail-Project

Project that provides automated functional test, such as login and logout, using selenium
webdriver, Java programing language and maven architecture.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Tests](#tests)
5. [Pages](#pages)

## Prerequisites
-Java (JDK)
-Web browser, Firefox or Chrome
-IntelliJ Community Edition (Opional)
-Windows operating system

## Installation
1. Clone the repository

```
git clone https://github.com/IvoOjvan/FERIT-Unios-Webmail-Project.git
```
2. Reload maven project
  -2.1 Right click on pom.xml
  -2.2 hover Maven
  -2.3 click "Reload project"

4. Download Allure binnaries

Allure binnaries can be downloaded at [Allure binnaries](https://github.com/allure-framework/allure2/releases/tag/2.25.0/)

4. Add Allure to IDE Path Variables

e.g. allure-2.25.0\allure-2.25.0\bin


## Usage
### 1. Enter project folder
    ```
    cd FERIT-Unios-Webmail-Testing
    ```
### 2. Run tests using testng.xml
In terminal enter this line (run with ctrl + enter):
    ```
    mvn test
    ```
### 3. See test resaults
In terminal enter this line:
    ```
    allure serve allure-results
    ```

## Pages
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


## Tests
### FunctionalTest class
According to best practices this class should contain WebDriver instance and methods needed to setup driver
before executing test

```java
public class FunctionalTest {

	protected static WebDriver driver;
	
	@BeforeClass
	public static void setUp(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@After
	public void cleanUp(){
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.close();
	}	
}
```

### Actual test class
Test class should extend FunctionalTest class and contain only test method
marked with @Test

```java
public class SignUpFormTest extends FunctionalTest {

	@Test
	public void signUp(){
		test code...
	}	
}
```

