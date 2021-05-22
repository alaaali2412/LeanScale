package tests.registerAccount;

import base.TestBase;
import org.testng.annotations.Test;
import pages.*;

public class TC01_RegisterNewUserTest extends TestBase {

    @Test
    public void RegisterNewUser() {
        PageGenerator.GetInstance( HomePage.class).clickCreateAnAccountBtn();
        PageGenerator.GetInstance( RegistrationPage.class).clearRegistrationFields();
        PageGenerator.GetInstance( RegistrationPage.class).addNewUser();
        PageGenerator.GetInstance( RegistrationPage.class).clickCreateNewAccountBtn();
        PageGenerator.GetInstance( ProfilePage.class).checkRegistrationSuccessMsg();
        PageGenerator.GetInstance( HomePage.class).ClickLogOut();
        PageGenerator.GetInstance( HomePage.class).assertSignOUtMsgDisplayed();
        PageGenerator.GetInstance( HomePage.class).clickSignInBtn();
        PageGenerator.GetInstance( LoginPage.class).userLogin();
        PageGenerator.GetInstance( LoginPage.class).clickSignInBtn();
        PageGenerator.GetInstance( HomePage.class).ClickLogOut();

    }
}
