package nxtTrendzTests;

import nxtTrendzPages.RegistrationPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import testNGListeners.TestListener;
import utility.ConfigReader;
import webdriver.BaseClass;

import java.time.Duration;
import java.util.Properties;

import static webdriver.BaseClass.driver;

@Listeners(TestListener.class)
public class RegistrationPageTest {
    private final Properties properties;
    private final WebDriverWait wait;
    RegistrationPage registrationPage;


    public RegistrationPageTest(){
        ConfigReader configReader = new ConfigReader();
        properties = configReader.init_prop();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @DataProvider(name = "registrationDetails")
    public Object[][] registrationDetails(){
            return new Object[][]{
                    {"Sneha","Sneha@gmail.com","Sneha@123","Female","India"},
                    {"Satya","Satya@gmail.com","Satya@123","Male","USA"},
                    {"Akhil","Akhil@gmail.com","Akhil@123","Male","UK"},
                    {"Arun","Arun@gmail.com","Arun@123","Male","Australia"},
                    {"Gowri","Gowri@gmail.com","Gowri@123","Female","Japan"},
                    {"Ganesh","Ganesh@gmail.com","Ganesh@123","Male","Russia"}
            };
    }

    @BeforeMethod
    public void setup(){
        BaseClass.launchBrowser();
        driver.get(properties.getProperty("registrationPageUrl"));
    }

    @Test(priority = 1)
    public void verifyRegistrationPage(){
        registrationPage = new RegistrationPage(driver);
//        System.out.println("Registration page url actual is: " + registrationPage.registrationPageUrl());
//        System.out.println("Registration page url expected is: " + properties.getProperty("registrationPageUrl"));
        Assert.assertEquals(registrationPage.registrationPageUrl(),properties.getProperty("registrationPageUrl"));
    }

    @Test(priority = 2, dataProvider = "registrationDetails")
    public void registerDetails(String username,String email,String password,String gender,String country){
        registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser(username,email,password,gender,country);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}