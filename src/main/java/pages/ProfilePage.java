package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProfilePage extends PageBase {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-ui-id= 'message-success']")
    private WebElement registrationSuccessMsg;


    @FindBy(xpath = "//*[@class='box-content']/p/br")
    private WebElement emailText;



    public void checkRegistrationSuccessMsg(){
        Assert.assertTrue(registrationSuccessMsg.isDisplayed());
        Assert.assertEquals(registrationSuccessMsg.getText(),"Thank you for registering with Main Website Store.");
    }
}
