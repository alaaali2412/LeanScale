package tests.registerAccount;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.RegistrationPage;

public class TC04_RegistrationEmailFormatValidationTest extends TestBase {

    @Test
    public void RegistrationEmailFormatValidation() {
        PageGenerator.GetInstance(HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).addInvalidEmailFormat();
        PageGenerator.GetInstance(RegistrationPage.class).checkInvalidEmailErrorMsg();
    }
}
