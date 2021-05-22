package tests.registerAccount;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.RegistrationPage;

public class TC03_UserCannotRegisterWithoutAddingMandatoryFieldsTest extends TestBase {

    @Test
    public void userCannotRegisterWithoutFillingMandatoryFields() {
        PageGenerator.GetInstance(HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).clickCreateNewAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).checkValidationMessages("This is a required field.");
    }
}
