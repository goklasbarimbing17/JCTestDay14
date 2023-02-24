package com.juaracoding.testday14;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestFormBiodata {
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static WebElement selectObject;

    static void setUpWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Software Testing\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get("https://formy-project.herokuapp.com/form");
        String title = driver.getTitle();
        System.out.println("Open Website : " + title);

        driver.manage().window().maximize();
        System.out.println("Maximize Website");
    }

    static void tearDown() {
        driver.quit();
        System.out.println("Completed Input Form Testing");
    }

    static void sleepTime() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static String getTitleElement(String locator) {
        String title = driver.findElement(By.xpath(locator)).getText();
        return title;
    }

    static void testInputForm() {
        //Input first-name
        driver.findElement(By.id("first-name")).sendKeys("Judika");
        System.out.println(getTitleElement("//label[@for='first-name']") + " successfully input");

        //Input last-name
        driver.findElement(By.id("last-name")).sendKeys("Sihotang");
        System.out.println(getTitleElement("//label[@for='last-name']") + " successfully input");

        //Input job-title
        driver.findElement(By.id("job-title")).sendKeys("Penyanyi");
        System.out.println(getTitleElement("//label[normalize-space()='Job title']") + " successfully input");

        //Select radio-button
        driver.findElement(By.id("radio-button-1")).click();
        System.out.println(getTitleElement("//label[normalize-space()='Highest level of education']") + " successfully selected");

        //Scroll by pixel (vertical)
        js.executeScript("window.scrollBy(0,500)");
        System.out.println("Scroll vertical");

        //Select Check-Box
        driver.findElement(By.xpath("//input[@id='checkbox-1']")).click();
        System.out.println(getTitleElement("//label[normalize-space()='Sex']") + " successfully selected");

        //Select-menu Year of experience
        selectObject = driver.findElement(By.xpath("//select[@id='select-menu']"));
        Select experience = new Select(selectObject);
        experience.selectByValue("3");
        System.out.println(getTitleElement("//label[@for='select-menu']")+" successfully selected");

        //select date
        driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("02/20/2023");
        System.out.println(getTitleElement("//label[normalize-space()='Date']")+" successfully selected");

        //Click button-submit
        driver.findElement(By.linkText("Submit")).click();
        System.out.println("Successfully clicked the submit button");

        //delay 3 seconds
        sleepTime();
        System.out.println("Sleep 3 Seconds");
    }

    public static void main(String[] args) {
        setUpWebDriver();
        testInputForm();
        tearDown();
    }
}
