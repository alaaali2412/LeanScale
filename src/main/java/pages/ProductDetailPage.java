package pages;

import base.PageBase;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailPage extends PageBase {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='swatch-option color']")
    private List<WebElement> colors;

    @FindBy(xpath = "//*[@class='swatch-option text']")
    private List<WebElement> sizes;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartBtn;

    @FindBy(id = "qty")
    private WebElement quantityField;

    @FindBy(id = "qty-error")
    private WebElement quantityFieldErrorMsg;

    @FindBy(xpath = "//*[@id = 'option-label-color-93']/following-sibling::span")
    private WebElement itemSelectedColor;

    @FindBy(xpath = "//*[@data-ui-id='message-success']")
    private WebElement addToCartSuccessMsg;

    @FindBy(id = "super_attribute[150]-error")
    private WebElement sizeErrorMsg;

    @FindBy(id = "super_attribute[93]-error")
    private WebElement colorErrorMsg;


    public void selectItemSize(String itemSize) {
        for (WebElement size : sizes) {
            if (size.getText().equalsIgnoreCase(itemSize)) {
                clickButton(size);
            }
        }
    }

    public void selectItemColor(String itemColorInRbga, String itemColor) {
        for (WebElement color : colors) {
            if (color.getCssValue("background-color").equals(itemColorInRbga)) {
                clickButton(color);
                Assert.assertEquals(itemColor, itemSelectedColor.getText());
                break;
            }
        }
    }

    public void clickAddToCart() {
        clickButton(addToCartBtn);
    }

    public void assertAddToCartSuccessMsg() {
        waitVisibilityOfElement(addToCartSuccessMsg);
        Assert.assertTrue(addToCartSuccessMsg.isDisplayed());
    }

    public void addInvalidQuantityForAnItem(String invalidQuantityValue) {
        clearField(quantityField);
        addText(quantityField, invalidQuantityValue);
    }

    public void assertInvalidItemQuantityErrorMsg() {
        Assert.assertEquals("Please enter a quantity greater than 0.", quantityFieldErrorMsg.getText());
    }

    public void assertColorAndSizeErrorMsg() {
        Assert.assertEquals("This is a required field.", sizeErrorMsg.getText());
        Assert.assertEquals("This is a required field.", colorErrorMsg.getText());
    }
}
