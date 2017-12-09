package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super( driver );
    }

    @FindBy(xpath = ".//li[@id='2416']")
    WebElement notebooks;

    @FindBy(xpath = ".//div[@class='container']//li[6]//a")
    WebElement appleNotebooks;

    @FindBy(xpath = ".//div[@id='catalog_goods_block']//a[contains(text(), 'MJLQ2UA/A')]")
    WebElement macBookProRetina15;

    @FindBy(xpath = ".//button[@name='topurchases']")
    WebElement buyButton;

    @FindBy(xpath = ".//div[@id='cart-popup']//span[contains(@class, 'cart-return-link')]")
    WebElement continueShoppingButton;

    @FindBy(xpath = ".//li[@id='3361']//a")
    WebElement smartphonesTVandElectronics;

    @FindBy(xpath = ".//ul[@class='f-menu-cols-b-i']//a[@href='https://rozetka.com.ua/mobile-phones/c80003/filter/preset=smartfon/']")
    WebElement smartphones;

//    @FindBy(xpath = ".//img[@alt='Apple iPhone 7 32GB Black']")
//    WebElement iphone7Card;

    @FindBy(xpath = ".//img[@alt='Apple iPhone 7 32GB Black']//following::div[@name='buy_catalog'][1]//button[@type='submit']")
    WebElement basketButtonInCard;

    @FindBy(xpath = ".//div[@id='cart-popup']//span[@name='cost']")
    WebElement cartTotalUAH;

    @FindBy(xpath = ".//div[@class='g-i-tile g-i-tile-catalog']//a[contains(text(), 'Apple iPhone 6 32GB Space Gray')]//following::div[@class='g-price-uah'][1]")
    WebElement iPhonePrice;

    @FindBy(xpath = ".//span[@id='price_label']")
    WebElement price;

    public void goToNotebooks() {
        actionsWithElements.clickAction( notebooks );
    }

    public void goToAppleNotebooks() {
        actionsWithElements.clickAction( appleNotebooks );
    }

    public void clickBuyButton() {
        actionsWithElements.clickAction( buyButton );
    }

    public void closeBasketPopUP() {
        actionsWithElements.clickAction( continueShoppingButton );
    }

    public void goTosmartphonesTVandElectronicsMenu() {
        actionsWithElements.clickAction( smartphonesTVandElectronics );
    }

    public void goToSmartphonesMenu() {
        actionsWithElements.clickAction( smartphones );
    }

    public void clickSmallBasketButton() {
        actionsWithElements.clickAction( basketButtonInCard );
    }

    public String getBasketTotal() {
        return actionsWithElements.getElementText( cartTotalUAH );
    }
//
//    public String getCardPrice(){
//        return actionsWithElements.getElementText( actionsWithElements.getElementByXPath(actionsWithElements.  ) );
//    }

    public String getPrice() {
        return actionsWithElements.getElementText( price );
    }


}
