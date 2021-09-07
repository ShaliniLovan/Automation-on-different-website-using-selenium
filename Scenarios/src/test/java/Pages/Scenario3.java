package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Scenario3 {

    WebDriver driver;
    public Logger logger = Logger.getLogger(Scenario3.class.getName());
    Screenshot screenshot = new Screenshot();
    ConstantFile constantFile=new ConstantFile();

    boolean visible=false;
    WebElement temp=null;


    //retrieving from constant class
    String url3= constantFile.Scenario3_url;

    By gallery_button = By.xpath("//a[text()='Gallery']");

    @BeforeTest
    public void open_url3()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url3);
    }

    @Test(priority = 0)
    public void openGalleryTab() throws InterruptedException {

        do {
            try
            {
                temp=driver.findElement(gallery_button);
                visible=temp.isDisplayed();
            }
            catch (Exception e)
            {
                driver.navigate().refresh();
            }
        }
        while(!visible);
        //click on display button
          temp.click();

          logger.info("Display Button Clicked Successfully");

        }

        @AfterTest
         public void close_url()
        {
            driver.close();
            logger.info("Closed Scenario3  window");
        }
    }


















//  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//By elem_dynamic = By.id("dynamic-element");
//wait.until(ExpectedConditions.presenceOfElementLocated(elem_dynamic));
