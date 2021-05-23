package tests.registerAccount;

import base.TestBase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.RegistrationPage;

public class TC02_RegisterWithExistEmailTest extends TestBase {
    @Test
    @Description("use same exist email to register a new account")
    public void RegisterWithExistEmail() {
        PageGenerator.GetInstance(HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).clearRegistrationFields();
        PageGenerator.GetInstance(RegistrationPage.class).registerWithExistEmail();
        PageGenerator.GetInstance(RegistrationPage.class).clickCreateNewAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).registerWithExistEmailErrorMsg();
    }
}
