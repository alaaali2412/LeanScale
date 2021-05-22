package base;


import connectors.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class PageBase {
    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickButton(WebElement element) {
        element.click();
    }

    protected void addText(WebElement element, String word) {
        element.sendKeys(word);
    }

    protected static void clearField(WebElement button) {
        button.clear();
    }

    protected void waitVisibilityOfElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected String propertiesFileName;

    public String getPropertiesFileName() {
        return propertiesFileName;
    }

    public void setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    public String getValuesFromPropertiesFile(String value) {
        Properties properties = new Properties();
        FileInputStream file;
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + getPropertiesFileName());
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not read properties from file: [" + getPropertiesFileName() + "].", e);
        }
        return properties.getProperty(value);
    }

    public String updateValueInPropertiesFile(String key, String value) {
        Properties properties = new Properties();
        FileInputStream fileIn;
        FileOutputStream fileOut;
        try {
            fileIn = new FileInputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + getPropertiesFileName());
            properties.load(fileIn);
            fileOut = new FileOutputStream(System.getProperty("user.dir") + "/PropertiesFiles/" + getPropertiesFileName());
            properties.setProperty(key, value);
            properties.store(fileOut, "updated successfully");
        } catch (IOException e) {

            System.out.println("Exception while taking screenshot" + e.getMessage());
            throw new RuntimeException("Could not read properties from file: [" + getPropertiesFileName() + "].", e);
        }
        return properties.getProperty(value);
    }


    public static String generateRandomText(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String randomString = sb.toString();
        return randomString;
    }


    protected static char[] generateRandomPassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];
        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));
        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

}
