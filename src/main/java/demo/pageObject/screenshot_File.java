package demo.pageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class screenshot_File {
    public static void Jatango(WebDriver driver, String screenshotName)
    {
        try {
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File source = scrShot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
            System.out.println("Screenshot Done");
        } catch (Exception e)
        {
            System.out.println("Exception while taking screenshot"+ e.getMessage());
        }
    }
}
//Code writing
//screenshot_File.SimplifyVms(driver,"Education_SubmitButton");