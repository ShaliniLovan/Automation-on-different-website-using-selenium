package Pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Scenario2 {

    WebDriver driver;
    public Logger logger = Logger.getLogger(Scenario2.class.getName());
    Screenshot screenshot = new Screenshot();
    ConstantFile constantFile=new ConstantFile();

    //retrieving from constant class
    String url2= constantFile.Scenario2_url;
    String upload_path=constantFile.Upload_file_path;

    By choose_file = By.xpath("//input[@class='dz-hidden-input']");
    By upload_file=By.xpath("//input[@id='file-submit']");

@BeforeTest
    public void open_url2()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url2);
        logger.info("Opened browser for Scenario2");
    }

@Test(priority = 0)
    public void upload_file() throws InterruptedException,AWTException {

        WebElement upload = driver.findElement(choose_file);

        //to press the upload button
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //to upload the file
        try
        {
            Runtime.getRuntime().exec(upload_path);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            logger.info("Text File uploaded");
            screenshot.take_screenshot("Uploaded File");
        }

        catch (Exception e) {
            logger.info("File not uploaded");
            screenshot.take_screenshot("Upload file error");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }

       WebElement upload_button=driver.findElement(upload_file);
       upload_button.click();
       Thread.sleep(2000);

        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        Thread.sleep(1000);
        Assert.assertEquals(element.getText(),"File Uploaded!");
        logger.info("Verified Message ");

    }
    @AfterTest()
    public void close_url()
    {
        driver.close();
        logger.info("Browser closed successfully for scenario 2");
    }
}