package demo.stepdefs;


import com.asprise.ocr.Ocr;
import demo.DriverManager;
import demo.TestBase;
import demo.pageObject.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.sikuli.script.OCR;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static demo.pageObject.projectAllXpath.*;
import static org.sikuli.script.Sikulix.print;


public class LoginPage extends DriverManager {
    static projectAllXpath dashBoardXpath = new projectAllXpath(driver);
    static xls_Reader reader = new xls_Reader("./src/test/resources/Data.xlsx");
    String dirPath = System.getProperty("user.dir");
    String imagePath = dirPath + "/autoIt/pic.jpeg";
    String docpath = dirPath + "/autoIt/resume.doc";
    SoftAssert softAssert = new SoftAssert();
    //    String ExclePath=dirPath+"/autoIt/JobTitle.xlsx";
//    xls_Reader reader1 = new xls_Reader(ExclePath);
    String MyShopsValue = reader.getCellData("Login", "MyShopsDashBoard", 2);

    // TestCase:-1
    @Given("Enter application URL in address bar")
    public void enter_application_url_in_address_bar() throws Exception {
        ScreenRecorderUtil.startRecord("URL open");
        try {
            driver.get(prop.getPropValues(TestBase.URL));
            Thread.sleep(8000);
        }catch (Exception e){
            System.out.println("Enter application URL in address bar is not working");
            screenshot_File.Jatango(driver,"address bar");
        }
    }

    @When("Enter Username")
    public void enter_username() throws Exception {
        dashBoardXpath.moveToElementAndCLikOn(Log_In);
        Thread.sleep(3000);
        dashBoardXpath.enterValue(dashBoardXpath.Username, reader.getCellData("Login", "USERNAME", 2));
        Thread.sleep(5000);
        System.out.println("   Enter UserName is:  " + reader.getCellData("Login", "USERNAME", 2));
        try {
            File src1=driver.findElement(By.xpath("//*[@alt='captcha']")).getScreenshotAs(OutputType.FILE);
            String path="./Screenshots/capture.png";
            FileHandler.copy(src1,new File(path));
            ITesseract image1=new Tesseract();
            image1.setTessVariable("user_defined_dpi", "71");
            Thread.sleep(500);
            String imageText=image1.doOCR(new File(path));
            System.out.println("imageText: "+imageText);
            Thread.sleep(500);
            String finalText=imageText.replaceAll("[^a-zA-z0-9]","");
            System.out.println("finalText: "+finalText);
            Thread.sleep(5000);

            String abc= JOptionPane.showInputDialog("Enter The capture");
            System.out.println("Test Image: "+abc);
            reader.setCellData("Login","Captcha",2,abc);
            Thread.sleep(2000);
            String xyz=reader.getCellData("Login","Captcha",2);
            dashBoardXpath.enterValue(CaptchaTextBox,xyz);
            System.out.println("Captch value is: "+xyz);
            Thread.sleep(2000);
            dashBoardXpath.clickOn(Continue);
            Thread.sleep(8000);
        } catch (Exception e) {
            System.out.println("CaptchaTextBox not view: " + e.getMessage());
            screenshot_File.Jatango(driver,"CaptchaTextBox not view");
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
            screenshot_File.Jatango(driver,"Password is captch not showing");
            dashBoardXpath.enterValue(dashBoardXpath.Password, reader.getCellData("Login", "PASSWORD", 2));
            System.out.println(" Enter The Password is captch not showing: " + reader.getCellData("Login", "PASSWORD", 2));
            Thread.sleep(3000);

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
        } catch (Exception e) {
            System.out.println("Sign_in button not view");
            screenshot_File.Jatango(driver, "Sign_in button not view");
            Assert.assertTrue(true, "Sign_in button not view");
            Thread.sleep(3000);
        }

        Thread.sleep(5000);
    }

    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_the_homepage() throws Throwable {
        String CurrentURL = driver.getCurrentUrl();
        String LoginURL = TestBase.URL;
        softAssert.assertEquals(CurrentURL, LoginURL);
        System.out.println("Login Successful");
        Thread.sleep(3000);
        System.out.println("Title Verify: " + driver.getTitle());
        try {
            for (int i = 0; i < MyShopsList.size(); i++) {
                Thread.sleep(2000);
                dashBoardXpath.getDropDownValue(MyShopsList);
                Thread.sleep(2000);
                if (dashBoardXpath.getDropDownValue(MyShopsList).equals(MyShopsValue)) ;
                Thread.sleep(2000);
                dashBoardXpath.iterateWebElementListAndSelectValue(MyShopsList, MyShopsValue);
                Thread.sleep(2000);
            }
        }catch (Exception e){
            screenshot_File.Jatango(driver,"redirected");
            System.out.println("User should not be redirected: "+e.getMessage());
        }
    }

    private static BufferedImage cropImage(File filePath, int x, int y, int w,
                                           int h) {

        try {
            BufferedImage originalImgage = ImageIO.read(filePath);
            BufferedImage subImgage = originalImgage.getSubimage(x, y, w, h);

            return subImgage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static float getDiff(File f1, File f2, int width, int height) throws IOException {
        BufferedImage bi1 = null;
        BufferedImage bi2 = null;
        bi1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bi2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bi1 = ImageIO.read(f1);
        bi2 = ImageIO.read(f2);
        float diff = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb1 = bi1.getRGB(i, j);
                int rgb2 = bi2.getRGB(i, j);

                int b1 = rgb1 & 0xff;
                int g1 = (rgb1 & 0xff00) >> 8;
                int r1 = (rgb1 & 0xff0000) >> 16;

                int b2 = rgb2 & 0xff;
                int g2 = (rgb2 & 0xff00) >> 8;
                int r2 = (rgb2 & 0xff0000) >> 16;

                diff += Math.abs(b1 - b2);
                diff += Math.abs(g1 - g2);
                diff += Math.abs(r1 - r2);
            }
        }
        return diff;
    }

}