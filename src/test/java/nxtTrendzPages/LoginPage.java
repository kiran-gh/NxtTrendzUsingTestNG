package nxtTrendzPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ConfigReader;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Properties properties;


    //Locators for the login page
    @FindBy(id = "username")
    public WebElement usernameInputEle;

    @FindBy(id = "password")
    public WebElement passwordInputEle;

    @FindBy(className = "login-button")
    public WebElement loginButtonEle;

    @FindBy(className = "error-message")
    public WebElement errorMessageEle;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ConfigReader configReader = new ConfigReader();
        properties = configReader.init_prop();
        PageFactory.initElements(driver,this);
    }

    public String loginPageUrl(){
        driver.get(properties.getProperty("loginPageUrl"));
        wait.until(ExpectedConditions.urlToBe(properties.getProperty("loginPageUrl")));
        return driver.getCurrentUrl();
    }
    public void enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInputEle));
        usernameInputEle.sendKeys(username);
    }
    public void enterPassword(String password) {
        passwordInputEle.sendKeys(password);
    }
    public void clickLoginButton(){
        loginButtonEle.click();
    }
    public String errorMessage(){
        wait.until(ExpectedConditions.visibilityOf(errorMessageEle));
        return errorMessageEle.getText();
    }
    public void validUserLogin(String username, String password) {
//        driver.get(properties.getProperty("loginPageUrl"));
        wait.until(ExpectedConditions.urlToBe(properties.getProperty("loginPageUrl")));
        enterUserName(username);
        enterPassword(password);
        loginButtonEle.click();
        wait.until(ExpectedConditions.urlToBe(properties.getProperty("homePageUrl")));
    }
    public void invalidUserLogin(String username, String password) {
//        driver.get(properties.getProperty("loginPageUrl"));
        wait.until(ExpectedConditions.urlToBe(properties.getProperty("loginPageUrl")));
        enterUserName(username);
        enterPassword(password);
        loginButtonEle.click();
    }
}