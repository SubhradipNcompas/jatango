/**
 * Created By Subhradip Sinha
 * Date: 9/12/2024
 * Project Name: jatango
 */

package demo.stepdefs;

import demo.DriverManager;
import demo.TestBase;
import demo.pageObject.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.asserts.SoftAssert;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletionStage;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import static demo.pageObject.projectAllXpath.*;
import static java.nio.file.Paths.get;


public class Shows extends DriverManager {

    static projectAllXpath dashBoardXpath = new projectAllXpath(driver);
    static xls_Reader reader = new xls_Reader("./src/test/resources/Data.xlsx");
    String dirPath = System.getProperty("user.dir");
    SoftAssert softAssert = new SoftAssert();
    String ShowsNameDetails = reader.getCellData("Shows", "ShowsName", 2);
    String NameofShows = ShowsNameDetails + getRandom(1000);

    @Given("the user is on the shows tab")
    public void the_user_is_on_the_shows_tab() throws Throwable {
        dashBoardXpath.checkElementVisibility(ShowsTab, 50);
        if (ShowsTab.isDisplayed()) {
            clickOn(ShowsTab);
            System.out.println("the user is on the shows tab");
            Thread.sleep(5000);
        } else {
            System.out.println("the user is not on the shows tab");
            screenshot_File.Jatango(driver, "the shows tab ");
        }
    }

    @When("the user navigates to the shows creation page")
    public void the_user_navigates_to_the_shows_creation_page() throws Throwable {
        if (CreateNewProduct.isDisplayed()) {
            clickOn(CreateNewProduct);
            Thread.sleep(3000);
            System.out.println(" the user navigates to the shows creation page: " + CreateShow.getText());
            clickOn(CreateShow);
            Thread.sleep(5000);
        } else {
            System.out.println("User Not the view Shows creation page");
            screenshot_File.Jatango(driver, "Shows CreatePage Not View");
            softAssert.assertTrue(true, "Shows CreatePage Not View");
            Thread.sleep(5000);
        }
    }

    @When("the user fills in the show details with valid information")
    public void the_user_fills_in_the_show_details_with_valid_information() throws Throwable {
        if (ShowsName.isDisplayed()) {
            clickOn(ShowsName);
            enterValue(ShowsName, NameofShows);
            System.out.println("the user fills in the show details with valid information: " + NameofShows);
            Thread.sleep(5000);
        } else {
            screenshot_File.Jatango(driver, "ShowsName");
            System.out.println("the user fills in the show details with not valid information");
        }
    }

    @When("the user submits the show creation form")
    public void the_user_submits_the_show_creation_form() throws Throwable {
        try {
            System.out.println("the user submits the show creation form: " + CreateShowLink.getText());
            dashBoardXpath.moveToElementAndCLikOn(CreateShowLink);
            Thread.sleep(8000);
        } catch (Exception e) {
            System.out.println("the user not submits the show creation form Error: " + CreateShowLink.getText());
            screenshot_File.Jatango(driver, "show creation form");
        }
    }

    @Then("the user adds the product to the show")
    public void the_user_adds_the_product_to_the_show() throws Throwable {
        if (AddSelectProduct.isDisplayed()) {
            dashBoardXpath.moveToElementAndCLikOn(AddSelectProduct);
            Thread.sleep(5000);
            String data = reader.getCellData("Product", "ProductNameData", 2);
            enterValue(ProductSearch, data);
            Thread.sleep(5000);
            iterateWebElementListAndSelectValue(ListofProduct, data);
            System.out.println("the user adds the product to the show: ");
            Thread.sleep(8000);
        } else {
            screenshot_File.Jatango(driver, "AddSelectProduct");
            System.out.println("the user not adds the product to the show: ");
        }
    }

    @Then("the user copies the show's link")
    public void the_user_copies_the_show_s_link() {
        try {
            String link = copyURL.getText();
            System.out.println("the user copies the show's link: " + reader.setCellData("Shows", "LiveURLLink", 2, link.trim()));
        } catch (Exception e) {
            screenshot_File.Jatango(driver, "Copy link");
            System.out.println("the user not copies the show's link: " + e.getMessage());
        }
    }

    @When("the user pastes the link in the browser")
    public void the_user_pastes_the_link_in_the_browser() throws Throwable {
        try {
            String LiveDemo = reader.getCellData("Shows", "LiveURLLink", 2);
            String Test = "https://" + LiveDemo;
            reader.setCellData("Shows", "LiveURLLink", 2, Test);
            Thread.sleep(2000);

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            StringSelection stringSelection1 = new StringSelection(Test);
            Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard1.setContents(stringSelection1, stringSelection1);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            dashBoardXpath.gotoTab(driver,1);
            driver.navigate().to(Test);
            driver.switchTo().window(Test);
        } catch (Exception e) {
            screenshot_File.Jatango(driver, "LiveDemo");
            System.out.println("the user not pastes the link in the browser: " + e.getMessage());
        }
    }

    @Then("the user should see the newly created show page")
    public void the_user_should_see_the_newly_created_show_page() throws Throwable {
        String CurrentTitle = driver.getTitle();
        if (CurrentTitle.equals("Jatango")) {
            softAssert.assertTrue(true, CurrentTitle);
            System.out.println("the user should see the newly created show page: " + CurrentTitle);
            System.out.println("the user should see the newly created show page: " + driver.getCurrentUrl());
            Thread.sleep(2000);
        } else {
            screenshot_File.Jatango(driver, "CurrentTitle");
            System.out.println("the user should not see the newly created show page");
        }
        ScreenRecorderUtil.stopRecord();


    }

}
