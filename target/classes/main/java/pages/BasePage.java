package pages;

import libs.ActionsWithElements;
import libs.ConfigData;
import libs.ExcelDriver;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

@RunWith(value = Parameterized.class)
public class BasePage {
    private WebDriver driver;
    private Logger logger;
    ActionsWithElements actionsWithElements;
    private ExcelDriver excelDriver;
    LoginPage loginPage;
    BasketPage basketPage;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        logger = Logger.getLogger( getClass() );
        actionsWithElements = new ActionsWithElements( driver );
        PageFactory.initElements( driver, this );
    }

    public void goToMainPage(String url) {
        try {
            driver.get( url );
            logger.info( "Main page opened" );
        } catch (Exception e) {
            logger.info( "Can't open Main page" );
            Assert.fail( "Can't open Main page" );
        }
    }

    @Parameterized.Parameters
    public Map<String, String> getTestData(String sheetName) {
        Map<String, String> testData = null;
        try {
            String testDataFilePath = ConfigData.getCfgValue( "DATA_FILE" );
            String testDataSheet = ConfigData.getCfgValue( sheetName );
            testData = ExcelDriver.getData( testDataFilePath, testDataSheet );
            logger.info( "Test data executed from excel file." );
        } catch (Exception e) {
            logger.error( "Can't execute test data from excel file" );
            Assert.fail( "Can't execute test data from excel file" );
        }
        return testData;
    }

//    public void myLogin() {
//        try {
//            Map<String, String> dataSet = getTestData( "VALID_DATA" );
//            String login = dataSet.get( "login" );
//            String pass = dataSet.get( "pass" );
//            //String username = dataSet.get( "username" );
//
//            goToMainPage();
//            loginPage.clickLoginLink();
//            loginPage.enterLogin( login );
//            loginPage.enterPassword( pass );
//            loginPage.clickSubmitButton();
//            logger.info( "Login successful" );
//
//
//        } catch (Exception e) {
//            logger.error( "Login failed" );
//            Assert.fail( "Login failed" );
//        }
//
//    }

    public void selectFromCatalogByName(String modelName) {
        try {
            String xpath = ".//div[@id='catalog_goods_block']//a[contains(text(), '" + modelName + "')]";
            WebElement element = actionsWithElements.getElementByXPath( xpath );
            actionsWithElements.clickAction( element );
            logger.info( "Model " + modelName + " selected." );

        } catch (Exception e) {
            logger.error( "Model not selected." );
            Assert.fail( "Model not selected." );
        }
    }

    public String getPriceFromCard(String modelName) {
        String price = "no price";
        try {
            String xpath = ".//div[@class='g-i-tile g-i-tile-catalog']//a[contains(text(), '" + modelName + "')]//following::div[@class='g-price-uah'][1]";
            WebElement element = actionsWithElements.getElementByXPath( xpath );
            actionsWithElements.getElementText( element );
            logger.info( "Price taken for " + modelName );
        } catch (Exception e) {
            logger.error( "Fail to get price from card " + modelName );
            Assert.fail( "Fail to get price from card " + modelName );
        }
        return price;
    }

    public void moveToCard(String modelName) {
        try {
            String xpath = ".//div[@id='catalog_goods_block']//a[contains(text(), '" + modelName + "')]";
            WebElement element = actionsWithElements.getElementByXPath( xpath );
            actionsWithElements.moveTo( element );
            logger.info( "Moved to " + modelName );
        } catch (Exception e) {
            logger.error( "Can't move to card " + modelName );
            Assert.fail( "Can't move to card " + modelName );
        }
    }

    public Integer parsePrice(String incPrice) {
        Integer price = null;
        try {
            String trimmedString = incPrice.replaceAll( " ", "" );
            price = Integer.parseInt( trimmedString );
            logger.info( "Price parsed to Integer " + price );
        } catch (Exception e) {
            logger.error( "Price not parsed" );
            Assert.fail( "Price not parsed" );

        }
        return price;
    }
}

