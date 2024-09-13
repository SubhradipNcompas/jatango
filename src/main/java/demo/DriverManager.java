package demo;

import demo.pageObject.xls_Reader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static WebDriver driver;
    public static ConfigurationManager prop = new ConfigurationManager();
    static xls_Reader reader = new xls_Reader("./src/test/resources/Data.xlsx");
    static String BrowserName = reader.getCellData("Login", "Browser", 2);

    static {
        if (BrowserName.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking","enable-automation"));
            driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            System.out.println("** Welcome Open to The Chrome Browser ** "+BrowserName);
        } else if (BrowserName.equals("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            System.out.println("** Welcome Open to The FireFox Browser ** "+BrowserName);
        }else if (BrowserName.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            System.out.println("** Welcome Open to The Edge Browser ** "+BrowserName);
        }else if (BrowserName.equals("Safari")) {
            WebDriverManager.safaridriver().setup();
            SafariOptions options = new SafariOptions();
            driver = new SafariDriver(options);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            System.out.println("** Welcome Open to The Safari Browser ** "+BrowserName);
        }else {
            System.out.println("Highlighting Cells that Do Not Meet a Criteria in Browser List");
        }
    }
}