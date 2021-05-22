package pages;

import base.PageBase;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends PageBase {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "firstname-error")
    private WebElement firstNameErrorMsg;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "lastname-error")
    private WebElement lastNameErrorMsg;

    @FindBy(id = "email_address")
    private WebElement emailField;

    @FindBy(id = "email_address-error")
    private WebElement emailFieldErrorMsg;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "password-error")
    private WebElement passwordFieldErrorMsg;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy(id = "password-confirmation-error")
    private WebElement confirmPasswordFieldErrorMsg;

    @FindBy(xpath = "//*[@class = 'action submit primary']")
    private WebElement createAnAccountBtn;

    @FindBy(xpath = "//*[@data-ui-id='page-title-wrapper' ]")
    private WebElement registrationPageHeader;

    @FindBy(xpath = "//*[@data-ui-id = 'message-error']")
    private WebElement registrationEmailErrorMsg;

    public void addNewUser() {
        String firstName = generateRandomText(5);
        String lastName = generateRandomText(5);
        String email = generateRandomText(7) + "@mail.com";
        String password = String.valueOf(generateRandomPassword(8));
        setPropertiesFileName("RegistrationData.properties");
        addText(emailField, email);
        addText(passwordField, password);
        addText(confirmPasswordField, password);
        addText(lastNameField, lastName);
        addText(firstNameField, firstName);
        updateValueInPropertiesFile("FirstName", firstName);
        updateValueInPropertiesFile("LastName", lastName);
        updateValueInPropertiesFile("Email", email);
        updateValueInPropertiesFile("Password", password);
        updateValueInPropertiesFile("ConfirmPassword", password);
    }

    public void clearRegistrationFields() {
        clearField(firstNameField);
        clearField(lastNameField);
        clearField(emailField);
        clearField(passwordField);
        clearField(confirmPasswordField);
    }

    public void clickCreateNewAccountBtn() {
        clickButton(createAnAccountBtn);
    }

    public void checkValidationMessages(String errorMessage) {
        Assert.assertEquals(errorMessage, firstNameErrorMsg.getText());
        Assert.assertEquals(errorMessage, lastNameErrorMsg.getText());
        Assert.assertEquals(errorMessage, emailFieldErrorMsg.getText());
        Assert.assertEquals(errorMessage, passwordFieldErrorMsg.getText());
        Assert.assertEquals(errorMessage, confirmPasswordFieldErrorMsg.getText());
    }

    public void registerWithExistEmail() {
        setPropertiesFileName("RegistrationData.properties");
        addText(passwordField, getValuesFromPropertiesFile("Password"));
        addText(confirmPasswordField, getValuesFromPropertiesFile("ConfirmPassword"));
        addText(lastNameField, getValuesFromPropertiesFile("LastName"));
        addText(emailField, getValuesFromPropertiesFile("Email"));
        addText(firstNameField, getValuesFromPropertiesFile("FirstName"));
    }

    public void registerWithExistEmailErrorMsg() {
        Assert.assertTrue(registrationEmailErrorMsg.isDisplayed());
        Assert.assertEquals("There is already an account with this email address. If you are sure that it is your email address, " +
                "click here to get your password and access your account.", registrationEmailErrorMsg.getText());
    }

    public void addInvalidEmailFormat() {
        addText(emailField, generateRandomText(7));
        emailField.sendKeys(Keys.ENTER);
    }

    public void checkInvalidEmailErrorMsg() {
        Assert.assertEquals("Please enter a valid email address (Ex: johndoe@domain.com)."
                , emailFieldErrorMsg.getText());
    }

    public void addInvalidPasswordFormat() {
        addText(passwordField, generateRandomText(8));
        emailField.sendKeys(Keys.ENTER);
    }

    public void checkInvalidPasswordErrorMsg() {
        Assert.assertEquals("Minimum of different classes of characters in password is 3. " +
                        "Classes of characters: Lower Case, Upper Case, Digits, Special Characters."
                , passwordFieldErrorMsg.getText());
    }


    public void verifyThatPassAndConfirmPassShouldMatch() {
        addText(firstNameField,generateRandomText(5));
        addText(lastNameField,generateRandomText(5));
        addText(emailField,generateRandomText(7) +"@mail.com");
        addText(passwordField,String.valueOf(generateRandomPassword(8)));
        addText(confirmPasswordField,String.valueOf(generateRandomPassword(8)));
        emailField.sendKeys(Keys.ENTER);
    }

    public void checkUnMatchPasswordErrorMsg() {
        //waitVisibilityOfElement(confirmPasswordFieldErrorMsg);
        Assert.assertEquals("Please enter the same value again.", confirmPasswordFieldErrorMsg.getText());
    }
}
