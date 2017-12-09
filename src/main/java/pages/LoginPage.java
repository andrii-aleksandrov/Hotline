package pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.ws.soap.Addressing;
import java.util.Map;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super( driver );
    }

    Logger logger = Logger.getLogger( getClass() );

    @FindBy(xpath = ".//*[@id='header_user_menu_parent']")
    private WebElement loginLink;

    @FindBy(xpath = ".//input[contains(@name, 'login')]")
    private WebElement loginField;

    @FindBy(xpath = ".//div[@name='simple_auth']//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//div[@name='simple_auth']//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='header_user_menu_parent']/a")
    private WebElement username;

    @FindBy(xpath = ".//div[@class='auth-message']//p[@name='email_hint']")
    private WebElement emailHint;

    @FindBy(xpath = "//h5[@name='password_hint']")
    private WebElement passwordHint;

    public void clickLoginLink() {
        try {
            actionsWithElements.clickAction( loginLink );
            logger.info( "Login link clicked" );
        } catch (Exception e) {
            logger.error( "Can't find login link" );
            Assert.fail( "Can't find login link" );
        }
    }

    public void enterLogin(String login) {
        actionsWithElements.enterText( loginField, login );
    }

    public void enterPassword(String password) {
        actionsWithElements.enterText( passwordField, password );
    }

    public void clickSubmitButton() {
        actionsWithElements.clickAction( submitButton );
    }


    public String getUsername() {
        return actionsWithElements.getUsernameText( username, getTestData( "VALID_DATA" ).get( "username" ) );
    }

    public void checkInvalidLoginAlert() {
        Assert.assertTrue( "Alert is not displayed", actionsWithElements.isOnThePage( emailHint ) );
    }

    public void checkInvalidPasswordAlert() {
        Assert.assertTrue( "Alert is not displayed", actionsWithElements.isOnThePage( passwordHint ) );
    }

    public void myLogin() {
        try {
            Map<String, String> dataSet = getTestData( "VALID_DATA" );
            String login = dataSet.get( "login" );
            String pass = dataSet.get( "pass" );

            goToMainPage("https://rozetka.com.ua/");
            clickLoginLink();
            enterLogin( login );
            enterPassword( pass );

            clickSubmitButton();
            logger.info( "Login successful" );

        } catch (Exception e) {
            logger.error( "Login failed" );
            Assert.fail( "Login failed" );
        }

    }

}
