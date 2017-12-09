package libs;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class HotlineShopLine {
    String id;
    String price;
    String warranty;
    String priceXPath;
    String warantyXPath;
    Logger logger = Logger.getLogger( getClass() );

    ActionsWithElements actionsWithElements = new ActionsWithElements();

    public HotlineShopLine(WebElement element) {

        id = actionsWithElements.returnTagValue( element, "data-id" );
        priceXPath = ".//div[@data-id='" + id + "']//a[@id='gotoshop-price'] ";
        price = actionsWithElements.getElementText( actionsWithElements.getElementByXPath( priceXPath ) );
        warantyXPath = ".//div[@data-id='" + id + "']//div[contains(@class, 'delivery-th')]//span";
        warranty = actionsWithElements.getElementText( actionsWithElements.getElementByXPath( warantyXPath ) );
        logger.info( id + " " + warranty + " " + price );
    }

    public void setId(WebElement element) {
        this.id = actionsWithElements.returnTagValue( element, "data-id" );
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getWarranty() {
        return warranty;
    }
}
