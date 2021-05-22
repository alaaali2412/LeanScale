package tests.registerAccount;

import base.TestBase;
import pages.PageGenerator;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

public class TC06_VerifyThatPasswordAndConfirmPasswordMustMatchTest extends TestBase {
    @Test
    public void VerifyThatPasswordAndConfirmPasswordMustMatch(){
        PageGenerator.GetInstance(HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).clearRegistrationFields();
        PageGenerator.GetInstance(RegistrationPage.class).verifyThatPassAndConfirmPassShouldMatch();
        PageGenerator.GetInstance(RegistrationPage.class).clickCreateNewAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).checkUnMatchPasswordErrorMsg();
    }
}
