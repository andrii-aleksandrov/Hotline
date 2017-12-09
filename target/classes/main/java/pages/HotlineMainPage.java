package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HotlineMainPage extends BasketPage {


    public HotlineMainPage(WebDriver driver) {
        super( driver );
    }

    @FindBy(xpath = ".//input[@id='searchbox']")
    private WebElement searchBox;

    @FindBy(xpath = ".//input[@id='doSearch']")
    private WebElement searchButton;

    @FindBy(xpath = ".//div[@id='live-search']//li[./a[contains(.,'Apple')] and ./a[contains(.,'SE')] and .//strong[contains(.,'iPhone')]]")
    private WebElement searchLinkIPhoneSE;


    @FindBy(xpath = ".//div[@id='live-search']//li[./a[contains(.,'Apple')] and ./a[contains(.,'7')] and .//strong[contains(.,'iPhone')]]")
    private WebElement searchLinkIPhone7;

    @FindBy(xpath = ".//li[@data-id='prices']//a")
    private WebElement allOffers;

    @FindBy(xpath = ".//div[@class='new-price-line']//div[@data-selector='price-line']")
    private List<WebElement> shopLines;

    @FindBy(xpath = ".//div[@data-selector='price-line']//a[@id='gotoshop-price']")
    private List<WebElement> prices;

    @FindBy(xpath = ".//div[@class='new-price-line']//div[contains(@class, 'delivery-th')]")
    private List<WebElement> warranty;

    @FindBy(xpath = ".//div[@data-selector='price-line']//a[@id='gotoshop-price-button']")
    public List<WebElement> links;

    public void search(String searchText) {
        actionsWithElements.enterText( searchBox, searchText );
        actionsWithElements.clickAction( searchLinkIPhone7 );
    }

    public void clickAllOffers() {
        actionsWithElements.clickAction( allOffers );
    }

    public int getNumberOflines() {
        return shopLines.size();
    }

    public WebElement getLineWebElement(int i) {
        return shopLines.get( i );
    }



}
