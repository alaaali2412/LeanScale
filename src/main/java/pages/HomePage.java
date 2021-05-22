package pages;

import base.PageBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends PageBase {

    @FindBy(xpath = "//header//div/ul/li[3]/a")
    private WebElement createAnAccountBtn;

    @FindBy(xpath = "//*[@class = 'logo']")
    private WebElement logoImage;

    @FindBy(xpath = "//header//span/button")
    private WebElement arrowBtn;

    @FindBy(xpath = "//header//li[2]//ul/li[3]/a")
    private WebElement signOutBtn;

    @FindBy(xpath = "//*[@data-ui-id ='page-title-wrapper']")
    private WebElement signOutMsg;

    @FindBy(xpath = "//header//li[2]/a")
    private WebElement signInBtn;

    @FindBy(className = "product-item")
    private List<WebElement> items;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateAnAccountBtn() {
        waitVisibilityOfElement(logoImage);
        clickButton(createAnAccountBtn);
    }

    public void ClickLogOut() {
        clickButton(arrowBtn);
        clickButton(signOutBtn);
        waitVisibilityOfElement(signOutMsg);
    }

    public void assertSignOUtMsgDisplayed() {
        Assert.assertTrue(signOutMsg.isDisplayed());
        Assert.assertEquals("You are signed out", signOutMsg.getText());
    }

    public void clickSignInBtn() {
        clickButton(signInBtn);
    }

    public void openProductDetailPageOfAnItem(String itemName) {
        for (WebElement item : items) {
            if (item.getText().contains(itemName)) {
                clickButton(item);
                break;
            }
        }
    }
}
