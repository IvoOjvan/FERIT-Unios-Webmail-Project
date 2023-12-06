# FERIT-Unios-Webmail-Project

Project that provides automated functional test, such as login and logout, using selenium
webdriver, Java programing language and maven architecture.

## Table of Contents
-[Installation](#installation)
-[Usage](#usage)
-[Contributing](#contributing)

## Prerequisites
-Java (JDK)
-Web browser, Firefox or Chrome
-IntelliJ Community Edition (Opional)
-Windows operating system

## Instalation
1. Clone the repository

```
git clone https://github.com/IvoOjvan/FERIT-Unios-Webmail-Project.git
```
2. Reload maven project

Right click on pom.xml, hover Maven and click "Reload project"

3. Download Allure binnaries

[Link Text](https://github.com/allure-framework/allure2/releases/tag/2.25.0)

4. Add Allure to IDE Path Variables

allure-2.25.0\allure-2.25.0\bin


## Usage
1. Enter project folder

cd FERIT-Unios-Webmail-Testing

2. Run tests using testng.xml

In terminal enter: mvn test (ctrl + enter)

3. See test resaults

In terminal enter: allure serve allure-results



