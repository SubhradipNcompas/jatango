/**
 * Created By Subhradip Sinha
 * Date: 9/11/2024
 * Project Name: jatango
 */

package demo.stepdefs;

import com.aventstack.extentreports.model.ScreenCapture;
import demo.DriverManager;
import demo.TestBase;
import demo.pageObject.projectAllXpath;
import demo.pageObject.screenshot_File;
import demo.pageObject.xls_Reader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static demo.pageObject.projectAllXpath.*;

public class CreateProduct extends DriverManager {
    static projectAllXpath dashBoardXpath = new projectAllXpath(driver);
    static xls_Reader reader = new xls_Reader("./src/test/resources/Data.xlsx");
    String dirPath = System.getProperty("user.dir");
    String imagePath = dirPath + "/autoIt/bag.jpeg";
    String docpath = dirPath + "/autoIt/resume.doc";
    SoftAssert softAssert = new SoftAssert();
    String ProductName=reader.getCellData("Product","ProductName",2);
    String value=ProductName+ getRandombill(1000);
    String Selectcategory=reader.getCellData("Product","SelectCategory",2);
    String SelectCategoryProduct =reader.getCellData("Product","SelectCategoryProduct",2);
    String SelectProduct = reader.getCellData("Product","SelectProduct",2);
    String Quantity=reader.getCellData("Product","QuantityStock",2);
    String PriceDetails=reader.getCellData("Product","Price",2);
    String Weight_Estimate=reader.getCellData("Product","WeightEstimate",2);
    String WeightValue=reader.getCellData("Product","Weight",2);

    @Given("User on the product creation page")
    public void user_on_the_product_creation_page() throws Throwable {
            if(CreateNewProduct.isDisplayed()){
                clickOn(CreateNewProduct);
                System.out.println(" User on the product creation page: "+CreateNewProduct.isDisplayed());
                Thread.sleep(500);
                System.out.println(" User on the product creation page: "+CreateProductNew.isDisplayed());
                clickOn(CreateProductNew);
                Thread.sleep(500);
            }else {
                System.out.println("User Not the view product creation page");
                screenshot_File.Jatango(driver,"CreatePage Not View");
                softAssert.assertTrue(true,"CreatePage Not View");
                Thread.sleep(500);
            }
    }

    @When("User fill in the product details with valid information")
    public void user_fill_in_the_product_details_with_valid_information()throws Throwable {
        try {
            //Upload Cover Photo
            clickOn(AddCoverPhoto);
            Runtime.getRuntime().exec("src/test/resources/All_PhotoAnd_Biodate/jatango.exe" + " " + " " + dirPath + "\\src\\test\\resources\\All_PhotoAnd_Biodate\\shopping.jpeg");
            Thread.sleep(2000);
            clickOn(CoverPhotoSave);
            System.out.println("User fill in the product details with valid information");
            Thread.sleep(500);

            //Product name * Field
            enterValue(EnterProductName, value);
            System.out.println("Product name * Field: " + reader.setCellData("Product", "ProductNameData", 2, value));
            Thread.sleep(500);

            //Category * dropdown Value Select
            dashBoardXpath.moveToElementAndCLikOn(SelectCategory);
            iterateWebElementListAndSelectValue(allCategory, Selectcategory);
            System.out.println("Category * dropdown Value SelectCategory: " + Selectcategory);
            Thread.sleep(500);

            iterateWebElementListAndSelectValue(allCategory, SelectCategoryProduct);
            System.out.println("Category * dropdown Value SelectCategoryProduct: " + SelectCategoryProduct);
            Thread.sleep(500);

            iterateWebElementListAndSelectValue(allCategory, SelectProduct);
            System.out.println("Category * dropdown Value SelectProduct: " + SelectProduct);
            Thread.sleep(500);

            //Price * required
            enterValue(Price, PriceDetails);
            System.out.println("Price * required: " + PriceDetails);
            Thread.sleep(500);

            //Weight * required
            enterValue(Weight, WeightValue);
            System.out.println("Weight * required: " + WeightValue);
            Thread.sleep(500);

            //Weight Estimate

            clickOn(WeightEstimate);
            getDropDownValue(WeightAllEstimate);
            iterateWebElementListAndSelectValue(WeightAllEstimate, Weight_Estimate);
            System.out.println("Weight_Estimate is required: " + Weight_Estimate);
            Thread.sleep(500);

            //Quantity in stock *
            enterValue(Quantity_in_stock, Quantity);
            System.out.println("Quantity in stock *: " + Quantity);
            Thread.sleep(500);

        }catch (Exception e){
            System.out.println("User not fill in the product details with valid information"+e.getMessage());
            screenshot_File.Jatango(driver,"not valid Information");
            Thread.sleep(500);
        }
    }
    @When("User submit the product creation form")
    public void user_submit_the_product_creation_form() throws Throwable {
        if(PublishProduct.isDisplayed()){
            clickOn(PublishProduct);
            System.out.println("User submit the product creation form");
            Thread.sleep(500);
        }else {
            System.out.println("User not submit the product creation form");
            screenshot_File.Jatango(driver,"submit button issue product creation");
            softAssert.assertTrue(true,"User not submit the product creation form");
            Thread.sleep(500);
        }
    }


    @Then("the new product should be listed in the product inventory")
    public void the_new_product_should_be_listed_in_the_product_inventory() throws Throwable {
            if(searchProduct.isDisplayed()){
                clickOn(searchProduct);
                enterValue(searchProduct,value);
                System.out.println("the new product should be listed in the product inventory: "+ProductName);
                Thread.sleep(3000);

            }
    }

}
