package loginTests;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class InvalidLogin extends BaseTest {

    public InvalidLogin(String browser) {
        super( browser );
    }

    @Test
    public void invalidLogin() {
        Map<String, String> dataSet = basePage.getTestData( "INVALID_DATA" );
        String login = dataSet.get( "login" );
        String pass = dataSet.get( "pass" );
        String username = dataSet.get( "username" );

        basePage.goToMainPage("https://rozetka.com.ua/");
        loginPage.clickLoginLink();
        loginPage.enterLogin( login );
        loginPage.enterPassword( pass );
        loginPage.clickSubmitButton();

        loginPage.checkInvalidLoginAlert();
    }

    @Test
    public void invalidLoginWrongLogin() {
        Map<String, String> dataSet = basePage.getTestData( "VALID_DATA" );
        String login = dataSet.get( "login" );
        String pass = dataSet.get( "pass" );
        String username = dataSet.get( "username" );

        basePage.goToMainPage("https://rozetka.com.ua/");
        loginPage.clickLoginLink();
        loginPage.enterLogin( "andrii.aleksandrov@gmail.com" );
        loginPage.enterPassword( pass );
        loginPage.clickSubmitButton();

        loginPage.checkInvalidLoginAlert();
    }

    @Test
    public void invalidLoginWrongPass() {
        Map<String, String> dataSet = basePage.getTestData( "VALID_DATA" );
        String login = dataSet.get( "login" );
        String pass = dataSet.get( "pass" );
        String username = dataSet.get( "username" );

        basePage.goToMainPage("https://rozetka.com.ua/");
        loginPage.clickLoginLink();
        loginPage.enterLogin( login );
        loginPage.enterPassword( "P@ssword1" );
        loginPage.clickSubmitButton();

        loginPage.checkInvalidPasswordAlert();
    }

//    @Test
//    public void invalidLoginValidData() {
//        Map<String, String> dataSet = basePage.getTestData( "VALID_DATA" );
//        String login = dataSet.get( "login" );
//        String pass = dataSet.get( "pass" );
//        String username = dataSet.get( "username" );
//
//        basePage.goToMainPage();
//        loginPage.clickLoginLink();
//        loginPage.enterLogin( login );
//        loginPage.enterPassword( pass );
//        loginPage.clickSubmitButton();
//        loginPage.checkInvalidLoginAlert();
//    }
}
