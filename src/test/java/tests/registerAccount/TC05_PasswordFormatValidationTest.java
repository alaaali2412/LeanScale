package tests.registerAccount;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import pages.RegistrationPage;

public class TC05_PasswordFormatValidationTest{
    @Test
    public void PasswordFormatValidation() {
        PageGenerator.GetInstance(HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance(RegistrationPage.class).addInvalidPasswordFormat();
        PageGenerator.GetInstance(RegistrationPage.class).checkInvalidPasswordErrorMsg();
    }
}
