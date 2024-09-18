package nxtTrendzPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.ConfigReader;

import java.time.Duration;
import java.util.Properties;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Properties properties;


    @FindBy(name = "name")
    public WebElement userNameInputELe;

    @FindBy(name = "email")
    public WebElement emailInputELe;

    @FindBy(name = "password")
    public WebElement passwordInputELe;

    @FindBy(id = "male")
    public WebElement radioMaleELe;

    @FindBy(id = "female")
    public WebElement radioFemaleELe;

    @FindBy(id = "country")
    public WebElement selectCountryELe;

    @FindBy(id = "terms")
    public WebElement termsCheckBoxELe;

    @FindBy(id = "submitBtn")
    public WebElement submitButtonELe;

    @FindBy(id = "error")
    public WebElement errorMessageELe;

    @FindBy(tagName = "h2")
    public WebElement successMessageELe;

    @FindBy(linkText = "Log in")
    public WebElement loginLinkELe;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ConfigReader configReader = new ConfigReader();
        properties = configReader.init_prop();
        PageFactory.initElements(driver,this);
    }

    public String registrationPageUrl(){
        driver.get(properties.getProperty("registrationPageUrl"));
        return driver.getCurrentUrl();
    }
    public void registerUser(String username,String email,String password,String gender,String country){
        driver.get(properties.getProperty("registrationPageUrl"));

        userNameInputELe.sendKeys(username);
        emailInputELe.sendKeys(email);
        passwordInputELe.sendKeys(password);
        if(gender.equalsIgnoreCase("male")){
            radioMaleELe.click();
        }else{
            radioFemaleELe.click();
        }
        Select selectCountry = new Select(selectCountryELe);
        switch (country){
            case "India":
                selectCountry.selectByVisibleText("India");
                break;
            case "USA":
                selectCountry.selectByVisibleText("USA");
                break;
            case "UK":
                selectCountry.selectByVisibleText("UK");
                break;
            case "Australia":
                selectCountry.selectByVisibleText("Australia");
                break;
            case "Japan":
                selectCountry.selectByVisibleText("Japan");
                break;
            case "Russia":
                selectCountry.selectByVisibleText("Russia");
                break;
        }
        termsCheckBoxELe.click();
        submitButtonELe.click();
        wait.until(ExpectedConditions.visibilityOf(successMessageELe));
        Assert.assertTrue(successMessageELe.isDisplayed());
        loginLinkELe.click();
    }






}