package baseTest;

import libs.ActionsWithElements;
import libs.ConfigData;

import libs.Utils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

/**
 * Base class for all tests
 */

@RunWith(Parameterized.class)
public class BaseTest {
    public WebDriver driver;
    public String browser;
    public Logger logger = Logger.getLogger( getClass() );
    public Utils utils = new Utils();
    public String pathToScreenShot;
    private boolean isTestPass = false;

    // declare new pages here
    public BasePage basePage;
    public LoginPage loginPage;
    public MainPage mainPage;
    public BasketPage basketPage;
    public HotlineMainPage hotlineMainPage;
    public ActionsWithElements actionsWithElements;
    public String handleHost;

    public BaseTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{"chrome"}
        //, {"fireFox"}, {"phantomJs"}
                //{ "iedriver" },{ "opera" }
        };
        return Arrays.asList( data );
    }

    @Rule
    public TestName testName = new TestName();

    /**
     * Makes actions before test
     */
    @Before

    public void setUp() throws IOException {
        File file = new File( "" );
        pathToScreenShot = file.getAbsolutePath() + "\\target\\screenshots\\"
                + this.getClass().getPackage().getName() + "\\"
                + this.getClass().getSimpleName() + "\\"
                + this.testName.getMethodName() + ".jpg";

//        try {
//            browser = ConfigData.getCfgValue( "DRIVER" );
//        } catch (IOException e) {
//            logger.error( "Can't get driver name from config data" );
//        }

        if ("fireFox".equals( browser )) {
            logger.info( "FireFox will be started " );
            File fileFF = new File( ConfigData.getCfgValue( "DRIVER_PATH" ) + "geckodriver.exe" );
            System.setProperty( "webdriver.gecko.driver", fileFF.getAbsolutePath() );
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability( "marionette", true );
            driver = new FirefoxDriver( capabilities );
            logger.info( "FireFox is started" );
        } else if ("chrome".equals( browser )) {
            logger.info( "Chrome will be started " );
            File fileFF = new File( ConfigData.getCfgValue( "DRIVER_PATH" ) + "chromedriver.exe" );
            System.setProperty( "webdriver.chrome.driver", fileFF.getAbsolutePath() );
            driver = new ChromeDriver();
            logger.info( "Chrome is started" );
        } else if ("iedriver".equals( browser )) {
            logger.info( "IE will be started " );
            File file1 = new File( ConfigData.getCfgValue( "DRIVER_PATH" ) + "MicrosoftWebDriver.exe" );
            System.setProperty( "webdriver.ie.driver", file1.getAbsolutePath() );
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability( CapabilityType.BROWSER_NAME, "IE" );
            capabilities.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true );
            capabilities.setCapability( InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true );
            driver = new InternetExplorerDriver();
            logger.info( "IE is started" );
        } else if ("opera".equals( browser )) {
            logger.info( "Opera will be started" );
            File fileOpera = new File( ConfigData.getCfgValue( "DRIVER_PATH" ) + "operadriver.exe" );
            System.setProperty( "webdriver.opera.driver", fileOpera.getAbsolutePath() );
            driver = new OperaDriver();
            logger.info( "Opera is started" );
        } else if ("phantomJs".equals( browser )) {
            logger.info( "PHANTOMJS will be started" );
            File filePhantomjs = new File( ConfigData.getCfgValue( "DRIVER_PATH" ) + "phantomjs.exe" );
            System.setProperty( "webdriver.phantomjs.driver", filePhantomjs.getAbsolutePath() );
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setJavascriptEnabled( true );
            caps.setCapability( "takesScreenshot", true );
            caps.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    "D:/QA/drivers/phantomjs.exe"
            );
            driver = new PhantomJSDriver( caps );
            logger.info( "PHANTOMJS is started" );
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
        handleHost = driver.getWindowHandle();

        // initialize new pages here
        basePage = new BasePage( driver );
        loginPage = new LoginPage( driver );
        mainPage = new MainPage( driver );
        basketPage = new BasketPage( driver );
        hotlineMainPage = new HotlineMainPage( driver );
        actionsWithElements = new ActionsWithElements( driver );
    }

    /**
     * Close driver after test done, makes screenshot
     */
    @After
    public void tearDown() {
        if (!(driver == null)) {
            if (!isTestPass) {
                utils.screenShot( pathToScreenShot, driver );
            }
            utils.screenShot( pathToScreenShot, driver );
            driver.quit();
        }
    }

    /**
     * Open url safely
     */
    public void open(String url) {
        try {
            driver.get( url );
            logger.info( "Page " + url + " opened" );
        } catch (Exception e) {
            logger.error( "Can't open page: " + url );
            Assert.fail( "Can't open page: " + url );
        }
    }

    protected void checkCriteria(String message, String actualResult
            , String expectedResult) {

        Assert.assertThat( message, actualResult, is( expectedResult ) );
        setTestPass();
    }

    protected void checkCriteria(String message, Integer actualResult
            , Integer expectedResult) {

        Assert.assertThat( message, actualResult, is( expectedResult ) );
        setTestPass();
    }

    private void setTestPass() {
        isTestPass = true;
    }

    public WebElement getElementByXPath(String xpath) {
        return driver.findElement( By.xpath( xpath ) );
    }

    public void waitAndClick(WebElement el) {
        try {
            WebDriverWait wait = new WebDriverWait( driver, 5, 200 );
            wait.until( ExpectedConditions.elementToBeClickable( el ) ).click();
        } catch (Exception e) {
            scrollToElement( el );
            el.click();
        }
    }

    private void scrollToElement(WebElement el) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript( "arguments[0].scrollIntoView(true);", el );
        }
    }
}

