package tests.addItemsToCart;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.ProductDetailPage;

public class TC04_AddingItemToCartWithSelectingSpecificationsTest extends TestBase {

    @Test
    public void AddingItemToCartWithSelectingSpecifications(){
        PageGenerator.GetInstance(HomePage.class).openProductDetailPageOfAnItem("Argus All-Weather Tank");
        PageGenerator.GetInstance(ProductDetailPage.class).clickAddToCart();
        PageGenerator.GetInstance(ProductDetailPage.class).assertColorAndSizeErrorMsg();
    }
}
