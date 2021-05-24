package tests.addItemsToCart;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PageGenerator;
import pages.ProductDetailPage;

public class TC02_RegisteredUserAddItemToCartTest extends TestBase {

    /* login then add item to cart by name
    * user select item color and item size then click add to cart
    * verify that item added by asserting the success message */
    @Test
    public void RegisteredUserAddItemToCart() {
        PageGenerator.GetInstance( HomePage.class).clickSignInBtn();
        PageGenerator.GetInstance( LoginPage.class).userLogin();
        PageGenerator.GetInstance( LoginPage.class).clickSignInBtn();
        PageGenerator.GetInstance(HomePage.class).openProductDetailPageOfAnItem("Breathe-Easy Tank");
        PageGenerator.GetInstance(ProductDetailPage.class).selectItemColor("rgba(239, 61, 255, 1)", "Purple");
        PageGenerator.GetInstance(ProductDetailPage.class).selectItemSize("M");
        PageGenerator.GetInstance(ProductDetailPage.class).clickAddToCart();
        PageGenerator.GetInstance(ProductDetailPage.class).assertAddToCartSuccessMsg();
    }
}
