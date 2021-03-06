package tests.addItemsToCart;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.ProductDetailPage;

public class TC01_GustUserAddItemToCartTest extends TestBase {

    /* logged out user add item to cart by name
     * user select item color and item size then click add to cart
     * verify that item added by asserting the success message */
    @Test
    public void GustUserAddItemToCart () {
        PageGenerator.GetInstance(HomePage.class).openProductDetailPageOfAnItem("Breathe-Easy Tank");
        PageGenerator.GetInstance(ProductDetailPage.class).selectItemSize("M");
        PageGenerator.GetInstance(ProductDetailPage.class).selectItemColor("rgba(239, 61, 255, 1)", "Purple");
        PageGenerator.GetInstance(ProductDetailPage.class).clickAddToCart();
        PageGenerator.GetInstance(ProductDetailPage.class).assertAddToCartSuccessMsg();
    }
}
