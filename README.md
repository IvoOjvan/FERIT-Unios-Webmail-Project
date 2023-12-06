# FERIT-Unios-Webmail-Project

Project that provides automated functional test, such as login and logout, using selenium
webdriver, Java programing language and maven architecture.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Installation](#installation)
3. [Usage](#usage)

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
  2.1 Right click on pom.xml
  2.2 hover Maven
  2.3 click "Reload project"

4. Download Allure binnaries

Allure binnaries can be downloaded at [Allure binnaries](https://github.com/allure-framework/allure2/releases/tag/2.25.0/)

4. Add Allure to IDE Path Variables

allure-2.25.0\allure-2.25.0\bin


## Usage
1. Enter project folder

cd FERIT-Unios-Webmail-Testing

2. Run tests using testng.xml

In terminal enter: mvn test (ctrl + enter)

3. See test resaults

In terminal enter: allure serve allure-results



