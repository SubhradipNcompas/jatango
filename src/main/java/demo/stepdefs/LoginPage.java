package demo.stepdefs;


import demo.DriverManager;
import demo.TestBase;
import demo.pageObject.projectAllXpath;
import demo.pageObject.screenshot_File;
import demo.pageObject.xls_Reader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static demo.pageObject.projectAllXpath.*;


public class LoginPage extends DriverManager {
    static projectAllXpath dashBoardXpath = new projectAllXpath(driver);
    static xls_Reader reader = new xls_Reader("./src/test/resources/Data.xlsx");
    String dirPath = System.getProperty("user.dir");
    String imagePath = dirPath + "/autoIt/pic.jpeg";
    String docpath = dirPath + "/autoIt/resume.doc";
    SoftAssert softAssert = new SoftAssert();
    //    String ExclePath=dirPath+"/autoIt/JobTitle.xlsx";
//    xls_Reader reader1 = new xls_Reader(ExclePath);
    String MyShopsValue=reader.getCellData("Login","MyShopsDashBoard",2);

    // TestCase:-1
    @Given("Enter application URL in address bar")
    public void enter_application_url_in_address_bar() throws Exception {
        driver.get(prop.getPropValues(TestBase.URL));
        Thread.sleep(8000);
    }

    @When("Enter Username")
    public void enter_username() throws Exception {
        dashBoardXpath.moveToElementAndCLikOn(Log_In);
        Thread.sleep(3000);
        dashBoardXpath.enterValue(dashBoardXpath.Username, reader.getCellData("Login", "USERNAME", 2));
        Thread.sleep(5000);
        System.out.println("   Enter UserName is:  " + reader.getCellData("Login", "USERNAME", 2));
        try{
            WebElement imageelement=driver.findElement(By.xpath("/html/body/div/main/section/div/div/div/div[1]/div/form/div[1]/div/div[2]/div[1]/img"));
            File src=imageelement.getScreenshotAs(OutputType.FILE);
            String Path="./Screenshots/capture.png";
            FileHandler.copy(src,new File(Path));
            Thread.sleep(3000);
            ITesseract image= new Tesseract();
            image.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            String str=image.doOCR(new File(Path));
            System.out.println("Image OCR Done: ");
            System.out.println(str.replaceAll("\\n"," "));

        }catch (Exception e){
            System.out.println("CaptchaTextBox not view: "+e.getMessage());
        }

    }

    @Then("Enter Password")
    public void enter_password() throws Exception {
        if (Sing_In.isDisplayed()) {
            dashBoardXpath.clickOnAfterElementIsVisible(Sing_In);
            Thread.sleep(3000);
            dashBoardXpath.enterValue(dashBoardXpath.Password, reader.getCellData("Login", "PASSWORD", 2));
            Thread.sleep(3000);
            System.out.println(" Enter The Password is: " + reader.getCellData("Login", "PASSWORD", 2));
        } else {
            dashBoardXpath.enterValue(dashBoardXpath.Password, reader.getCellData("Login", "PASSWORD", 2));
            Thread.sleep(3000);
            System.out.println(" Enter The Password is captch not showing: " + reader.getCellData("Login", "PASSWORD", 2));
        }
    }

    @Then("Click Sing_In")
    public void click_sing_in() throws Exception {
        Thread.sleep(3000);
        WebElement SingIn = Sing_In;
        try {
            if (SingIn.isDisplayed()) {
                dashBoardXpath.clickOn(SingIn);
                System.out.println("************Click The Sing_In Button************");
                Thread.sleep(5000);
            } else {
                System.out.println("************Click The Sing_In Button Related Issue************");
                Thread.sleep(3000);
            }
        }catch (Exception e){
            System.out.println("Sign_in button not view");
            screenshot_File.Jatango(driver,"Sign_in button not view");
            Assert.assertTrue(true,"Sign_in button not view");
            Thread.sleep(3000);
        }

        Thread.sleep(5000);
    }
    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_the_homepage() throws Throwable{
        String CurrentURL=driver.getCurrentUrl();
        String LoginURL=TestBase.URL;
        softAssert.assertEquals(CurrentURL,LoginURL);
        System.out.println("Login Successful");
        Thread.sleep(3000);
        System.out.println("Title Verify: "+driver.getTitle());

        dashBoardXpath.moveToElementAndCLikOn(MyShops);

    }
}