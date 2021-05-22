package tests.addItemsToCart;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.ProductDetailPage;

public class TC03_EditItemQuantityToInValidValuesTest extends TestBase {

    @Test
    public void EditItemQuantityToInValidValues()  {

        //add quantity 0 value
        PageGenerator.GetInstance(HomePage.class).openProductDetailPageOfAnItem("Argus All-Weather Tank");
        PageGenerator.GetInstance(ProductDetailPage.class).addInvalidQuantityForAnItem("0");
        PageGenerator.GetInstance(ProductDetailPage.class).clickAddToCart();
        PageGenerator.GetInstance(ProductDetailPage.class).assertInvalidItemQuantityErrorMsg();
        //add quantity negative value
        PageGenerator.GetInstance(ProductDetailPage.class).addInvalidQuantityForAnItem("-3");
        PageGenerator.GetInstance(ProductDetailPage.class).clickAddToCart();
        PageGenerator.GetInstance(ProductDetailPage.class).assertInvalidItemQuantityErrorMsg();
    }
}
