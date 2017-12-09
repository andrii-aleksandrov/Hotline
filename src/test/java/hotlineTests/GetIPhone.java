package hotlineTests;

import baseTest.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.*;

public class GetIPhone extends BaseTest {

    private String keyPhrase;

    public GetIPhone(String browser) {
        super( browser );
    }

    @Test
    public void findIPhone() throws IOException, InterruptedException {

        String id;
        String price;
        String warranty;
        String reviews;
        String priceXPath;
        String warrantyXPath;
        String reviewsXPath;
        String warrantyString;
        Integer warrantyInteger;
        Integer warrantyTerm;
        Integer reviewsQnt;
        Integer priceInteger;
        String expectedURL;

        Map<String, String> dataSet = basePage.getTestData( "HOTLINE_DATA" );
        keyPhrase = dataSet.get( "keyPhrase" );
        warrantyString = dataSet.get( "minWarrantyTerm" );
        expectedURL = dataSet.get( "expectedURL" );
        //browser = dataSet.get( "browser" );

        Logger logger = Logger.getLogger( getClass() );

        open( "http://hotline.ua/" );

        hotlineMainPage.search( "iPhone" );
        hotlineMainPage.clickAllOffers();

        Map<String, Integer> newMap = new HashMap<>();
        int numberOfLines = hotlineMainPage.getNumberOflines();

        for (int i = 0; i < numberOfLines; i++) {

            WebElement element = hotlineMainPage.getLineWebElement( i );
            id = element.getAttribute( "data-id" );

            priceXPath = ".//div[@data-id='" + id + "']//a[@id='gotoshop-price']";
            price = getElementByXPath( priceXPath ).getText();

            warrantyXPath = ".//div[@data-id='" + id + "']//div[contains(@class, 'delivery-th')]";
            warranty = getElementByXPath( warrantyXPath ).getText();

            try {
                reviewsXPath = ".//div[@data-id='" + id + "']//div[contains(@class, 'sales-link')]//a";
                reviews = getElementByXPath( reviewsXPath ).getText();
            } catch (Exception e) {
                reviews = "";
            }

            warrantyInteger = getIntegerWarranty( warranty );
            warrantyTerm = Integer.parseInt( warrantyString );
            reviewsQnt = getIntegerReviews( reviews );
            priceInteger = getIntegerPrice( price );

            if ((warrantyInteger >= warrantyTerm && warrantyInteger != 999) && (reviewsQnt > 10)) {
                try {
                    newMap.put( id, priceInteger );
                } catch (Exception e) {
                    logger.error( "Cant create map" );
                }
            }
        }

// get id with minimum price value

        Map.Entry<String, Integer> min = Collections.min( newMap.entrySet(),
                Comparator.comparingInt( Map.Entry::getValue ) );
        String lessPriceID = min.getKey();

        String cheapestProductXpath = ".//div[@data-id='" + lessPriceID + "']//a[@id='gotoshop-price-button']";
        WebElement cheapestLink = getElementByXPath( cheapestProductXpath );

        waitAndClick( cheapestLink );

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window( winHandle );
        }

        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals("Result is true", expectedURL, actualURL );
    }

    private Integer getIntegerWarranty(String warrantyString) throws IOException {
        Integer warrantyInteger = 999;
        if (warrantyString.contains( keyPhrase )) {
            try {
                String tempString = warrantyString.substring( 0, warrantyString.indexOf( ' ' ) );
                warrantyInteger = Integer.parseInt( tempString );
            } catch (Exception e) {
                logger.error( "Can't parse warranty to Integer" );
            }
        }
        return warrantyInteger;
    }

    private Integer getIntegerReviews(String reviewsString) throws IOException {
        Integer reviewsInteger = 0;
        if (reviewsString.contains( " " )) {
            try {
                String tempString = reviewsString.substring( reviewsString.indexOf( ':' ) + 2 );
                reviewsInteger = Integer.parseInt( tempString );
            } catch (Exception e) {
                logger.error( "Can't parse reviews to Integer" );
            }
        }
        return reviewsInteger;
    }

    private Integer getIntegerPrice(String priceString) throws IOException {
        Integer priceInteger = 0;
        try {
            String tempString = priceString.substring( 0, priceString.indexOf( ',' ) ).replace( " ", "" );
            priceInteger = Integer.parseInt( tempString );
        } catch (Exception e) {
            logger.error( "Can't parse price to Integer: " + priceString );
        }
        return priceInteger;
    }
}