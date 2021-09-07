package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;

public class Scenario4 {

    WebDriver driver;
    public Logger logger = Logger.getLogger(Scenario2.class.getName());

    Screenshot screenshot = new Screenshot();
    ConstantFile constantFile=new ConstantFile();

    //retrieving from constant class
    String url= constantFile.Scenario4_url;

    //xPaths
    By close_tab=By.xpath("//p[text()='Close']");


    @BeforeTest
    public void open_url4()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }
    @Test(priority = 0)
    public void move_cursor() throws AWTException, InterruptedException {

        Robot robot = new Robot();
        robot.mouseMove(0, 0);
        logger.info("pointer is moved away from the view panel");
        Thread.sleep(2000);

        //click on close button
        WebElement close=driver.findElement(close_tab);
        logger.info("close tab clicked");
        close.click();
    }
    @AfterTest
    public void close_window()
    {
        driver.close();
        logger.info("Browser closed successfully for scenario 4");
    }
}

