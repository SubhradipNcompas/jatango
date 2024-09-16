package demo;

import java.util.concurrent.TimeUnit;

import static demo.DriverManager.driver;

public class TestBase {

    public static final String URL ="Jatango.url";


    public final static long explicitWait = 10000;
    public final static long impliciteWait = 10000;


    public static long getExplicitwait() {
        return explicitWait;
    }
    public static long getImplicitewait(int wait) {
        driver.manage().timeouts().implicitlyWait(wait,TimeUnit.SECONDS);
        return impliciteWait;
    }
}
