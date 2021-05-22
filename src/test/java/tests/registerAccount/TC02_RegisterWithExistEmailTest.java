package tests.registerAccount;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.RegistrationPage;

public class TC02_RegisterWithExistEmailTest extends TestBase {
    @Test
    public void RegisterWithExistEmail() {
        PageGenerator.GetInstance(HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).clearRegistrationFields();
        PageGenerator.GetInstance(RegistrationPage.class).registerWithExistEmail();
        PageGenerator.GetInstance(RegistrationPage.class).clickCreateNewAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).registerWithExistEmailErrorMsg();
    }
}
