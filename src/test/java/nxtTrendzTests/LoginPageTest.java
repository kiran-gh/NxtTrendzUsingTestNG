package nxtTrendzTests;

import nxtTrendzPages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.*;
import testNGListeners.TestListener;
import utility.ConfigReader;
import webdriver.BaseClass;

import java.time.Duration;
import java.util.Properties;

import static webdriver.BaseClass.*;

@Listeners(TestListener.class)
public class LoginPageTest  {
    LoginPage loginPage;
    private final Properties properties;
    private final WebDriverWait wait;

    public LoginPageTest(){
        ConfigReader configReader = new ConfigReader();
        properties = configReader.init_prop();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @DataProvider(name = "incorrectCredentials")
    public Object[][] incorrectCredentials(){
        return new Object[][]{
                {"InvalidUserName","rahu", "rahul@2021"},
                {"InvalidPassword","rahul", "rahul@2022"},
                {"EmptyPassword","rahul", ""},
                {"EmptyUserName","", "rahul@2021"},
                {"EmptyCredentials","", ""}
        };
    }


    @BeforeMethod
    public void setUp(){
        BaseClass.launchBrowser();
        driver.get(properties.getProperty("loginPageUrl"));
    }

    @Test(priority = 1)
    public void verifyLoginPage(){
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.loginPageUrl(),properties.getProperty("loginPageUrl"));
    }
    @Test(priority = 2)
    public void validCredentials(){
        loginPage = new LoginPage(driver);
        loginPage.validUserLogin(properties.getProperty("username"),properties.getProperty("password"));
        Assert.assertEquals(driver.getCurrentUrl(),properties.getProperty("homePageUrl"));
    }
    @Test(priority = 3, dataProvider = "incorrectCredentials")
    public void inValidCredentials(String scenario,String username, String password){
        loginPage = new LoginPage(driver);
        loginPage.invalidUserLogin(username,password);
        switch (scenario){
            case "InvalidCredentials":
                wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessageEle));
            Assert.assertEquals(loginPage.errorMessage(),properties.getProperty("invalidCredentialsError"));
            case "EmptyPassword":
                wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessageEle));
            Assert.assertEquals(loginPage.errorMessage(),properties.getProperty("emptyPasswordError"));
            case "EmptyUsername":
                wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessageEle));
            Assert.assertEquals(loginPage.errorMessage(),properties.getProperty("emptyUserNameError"));
            case "invalidPasswordError":
                wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessageEle));
            Assert.assertEquals(loginPage.errorMessage(),properties.getProperty("invalidPasswordError"));
            case "invalidUsernameError":
                wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessageEle));
            Assert.assertEquals(loginPage.errorMessage(),properties.getProperty("invalidUserNameError"));
            case "EmptyCredentials":
                wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessageEle));
            Assert.assertEquals(loginPage.errorMessage(),properties.getProperty("emptyCredentialsError"));
        }
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}