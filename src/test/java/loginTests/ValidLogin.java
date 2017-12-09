package loginTests;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import java.util.Map;

public class ValidLogin extends BaseTest {

    public ValidLogin(String browser) {
        super( browser );
    }

    @Test
    public void validLogin() {
        basePage.goToMainPage("https://rozetka.com.ua/");
        loginPage.clickLoginLink();
        loginPage.enterLogin( "aleksandrov.andrii@gmail.com" );
        loginPage.enterPassword( "A13794613as#" );
        loginPage.clickSubmitButton();

        checkCriteria( "User name not found.", loginPage.getUsername(), "Andrii" );
    }

    @Test
    public void validLoginWithTestData() {
        Map<String, String> dataSet = basePage.getTestData( "VALID_DATA" );
        String login = dataSet.get( "login" );
        String pass = dataSet.get( "pass" );
        String username = dataSet.get( "username" );

        basePage.goToMainPage("https://rozetka.com.ua/");
        loginPage.clickLoginLink();
        loginPage.enterLogin( login );
        loginPage.enterPassword( pass );
        loginPage.clickSubmitButton();

        checkCriteria( "User name not found.", loginPage.getUsername(), username );
    }
}
