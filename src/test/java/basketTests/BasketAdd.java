package basketTests;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BasketAdd extends BaseTest {

    public BasketAdd(String browser) {
        super( browser );
    }

    @Test
    public void BasketTestWithLogin() {
        String notebookPrice;

        loginPage.myLogin();

        // Navigate to notebooks
        basketPage.goToNotebooks();
        basketPage.goToAppleNotebooks();

        //Select notebook by name
        basePage.selectFromCatalogByName( "Apple MacBook Pro Retina 15\" (MJLQ2UA/A)" );

        //Get notebook price
        notebookPrice = basketPage.getPrice();

        //Add notebook to cart
        basketPage.clickBuyButton();

        String total = basketPage.getBasketTotal();
        Integer notePrice = basePage.parsePrice( notebookPrice );
        Integer totalPrice = basePage.parsePrice( total );

        checkCriteria( "Price does not match", notePrice, totalPrice );
    }

    @Test
    public void BasketTest() {
        String notebookPrice;
        String smartphonePrice;

        //basePage.myLogin();
//
//        Map<String, String> dataSet = basePage.getTestData( "VALID_DATA" );
//        String login = dataSet.get( "login" );
//        String pass = dataSet.get( "pass" );
//        String username = dataSet.get( "username" );


//        loginPage.clickLoginLink();
//        loginPage.enterLogin( login );
//        loginPage.enterPassword( pass );
//        loginPage.clickSubmitButton();
//        logger.info( "Login successful" );


        open( "https://rozetka.com.ua/" );

        // Navigate to notebooks
        basketPage.goToNotebooks();
        basketPage.goToAppleNotebooks();

        //Select notebook by name
        basePage.selectFromCatalogByName( "Apple MacBook Pro Retina 15\" (MJLQ2UA/A)" );

        //Get notebook price
        notebookPrice = basketPage.getPrice();

        //Add notebook to cart
        basketPage.clickBuyButton();


//        basketPage.closeBasketPopUP();
//
//        //Navigate to smartphone
//        basketPage.goTosmartphonesTVandElectronicsMenu();
//        basketPage.goToSmartphonesMenu();
//
//        //Select smartphone
//        basketPage.moveToCard( "Apple iPhone 7 32GB Black" );
//        smartphonePrice = basePage.getPriceFromCard( "Apple iPhone 7 32GB Black" );
//        basketPage.clickSmallBasketButton();
//        Integer secondprice = Integer.parseInt( smartphonePrice );

        String total = basketPage.getBasketTotal();
        Integer notePrice = basePage.parsePrice( notebookPrice );
        Integer totalPrice = basePage.parsePrice( total );

        checkCriteria( "Price does not match", notePrice, totalPrice );
    }
}
