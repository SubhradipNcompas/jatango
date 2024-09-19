package demo.pageObject;

import org.openqa.selenium.By;

import java.time.Month;

import static demo.DriverManager.driver;

public class selectDateRange {
    public static void selectDateRange(String startdt, String endDt) throws InterruptedException {
        selectDtInRange(startdt, "//div[contains(@class,'datepicker left-rangepicker-right')]");
        selectDtInRange(endDt, "//div[contains(@class,'datepicker right-rangepicker-left')]");
    }

    private static void selectDtInRange(String dt, String container) throws InterruptedException {
        String year = dt.split("/")[2];
        int month = Integer.parseInt(dt.split("/")[1]);
        String day = dt.split("/")[0];
        System.out.println("String date is "+dt+" year"+ " "+year + " "+" month" + " "+ month + " "+ " day"+ " "+ day);
        driver.findElement(By.xpath(container+"//nav/div[2]/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(container+"//nav/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='"+year+"']")).click();
        Thread.sleep(1000);
        String temp = Month.of(month).name().toLowerCase();
        String monthName = temp.substring(0, 1).toUpperCase() + temp.substring(1);
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and translate(normalize-space(text()) ,'abcdefghijklmnopqrstuvwxyz' ,'ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+ Month.of(month).name()+"']")).click();
        driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='"+ monthName+"']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='"+day+"']]")).click();
    }

    private static void selectEndDate(String dt, String container) throws InterruptedException {
        String year = dt.split("/")[2];
        int month = Integer.parseInt(dt.split("/")[1]);
        String day = dt.split("/")[0];
        System.out.println("String date is "+dt+" year"+ " "+year + " "+" month" + " "+ month + " "+ " day"+ " "+ day);
        driver.findElement(By.xpath(container+"//nav/div[2]/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(container+"//nav/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-year') and normalize-space(text())='"+year+"']")).click();
        Thread.sleep(1000);
        String temp = Month.of(month).name().toLowerCase();
        String monthName = temp.substring(0, 1).toUpperCase() + temp.substring(1);
        //driver.findElement(By.xpath("//div[@class='datepicker']//div[contains(@class,'datepicker--cell-month') and translate(normalize-space(text()) ,'abcdefghijklmnopqrstuvwxyz' ,'ABCDEFGHIJKLMNOPQRSTUVWXYZ')='"+ Month.of(month).name()+"']")).click();
        driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-month') and normalize-space(text())='"+ monthName+"']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(container+"//div[contains(@class,'datepicker--cell-day') and not(contains(@class,'other-month'))][span[text()='"+day+"']]")).click();
    }



}
