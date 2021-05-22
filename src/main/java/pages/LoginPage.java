package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passwordField;

    @FindBy(id = "send2")
    private WebElement signInBtn;

    public void userLogin(){
        setPropertiesFileName("RegistrationData.properties");
        addText(emailField,getValuesFromPropertiesFile("Email"));
        addText(passwordField,getValuesFromPropertiesFile("Password"));
    }

    public void clickSignInBtn(){
        clickButton(signInBtn);
    }

}
