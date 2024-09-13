package demo.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class driverStorage {
    private WebDriver driver;


    public driverStorage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(how = How.XPATH,using = "//input[@name='email']")
    public WebElement username;

    @FindBy(how =How.XPATH,using = "//input[@name='password']")
    public WebElement password;

    @FindBy(how =How.XPATH,using =  "//button[@type='submit']")
    public WebElement submitButton;

    public void enterValue(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void clickOn(WebElement submitButton) {
        submitButton.click();
    }

    public void checkElementVisibility(WebElement element, long timeinMillisecond) {

    }
}
