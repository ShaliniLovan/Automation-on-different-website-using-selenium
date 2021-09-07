package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Scenario1 {

    static WebDriver driver;

    public Logger logger = Logger.getLogger(Scenario1.class.getName());


    Screenshot screenshot = new Screenshot();
    ConstantFile constantFile = new ConstantFile();

    //retrieving from constant class
    String url = constantFile.Scenario1_url;
    String CSV_PATH = constantFile.Training_list;

    //xPaths
    By iFrame = By.xpath("//li[@id=\"iFrame\"]");
    By Current_filter = By.xpath("//span[@id='current_filter']");
    By Appium_Training = By.xpath("//img[@alt='Appium Training']");
    By Trainings = By.xpath("//div[@id='portfolio_filter']//li");
    By Open_new_tab = By.xpath("//li[@id=\"Open New Tab\"]");
    By Close_Msg = By.xpath("//a[@class=\"close_img\"]");
    By Click_here = By.xpath("//a[contains(text(),'Click Here')]");
    By DragDrop = By.xpath("//span[contains(text(),\"Drag And Drop\"]");
    By ClickDragandDrop_button= By.xpath("//*[@id=\"menu-item-2804\"]/a");
    By Trash_Frame=By.xpath("//*[@id=\"post-2669\"]/div[2]/div/div/div[1]/p/iframe");
    By Trash_item_list=By.xpath("//div[@id='trash']");
    By Capture_Text=By.xpath("//strong[contains(text(),\"What Unique We Serve?\")]/parent::h3/following-sibling::p");

    @BeforeTest()
    public void open_url() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test(priority = 0)
    public void setiFrame() throws InterruptedException {
        //click on iframe button
        WebElement click_iframe = driver.findElement(iFrame);
        click_iframe.click();

        driver.switchTo().frame("globalSqa");

        //click dropdown menu button
        Thread.sleep(2000);
        driver.findElement(Current_filter).click();

    }

    @Test(priority = 1)
    public void selectTraining() throws IOException, InterruptedException {

        //Select course
        BufferedReader reader = new BufferedReader(new FileReader(CSV_PATH));
        String[] courses = reader.readLine().split(",");

        //Storing listed trainings in list
        List<WebElement> training_list = driver.findElements(Trainings);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        for (int i = 0; i < training_list.size(); i++) {
            Thread.sleep(2000);

            if (training_list.get(i).getText().contains(courses[0])) {
                training_list.get(i).click();
                //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                logger.info("Selected training");
                break;
            }
        }
    }
    @Test(priority = 2)
    public void hoverMethod () throws InterruptedException {

        //Click the Appium card
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(2000);
        WebElement appium = driver.findElement(Appium_Training);
        Actions action = new Actions(driver);
        action.moveToElement(appium).build().perform();
        Thread.sleep(1000);
        appium.click();
        Thread.sleep(1000);

        screenshot.take_screenshot("appium card");

        //scroll down
        js.executeScript("window.scrollBy(0,1150)", "");

    }


    @Test(priority = 3)
    public void getContents () throws InterruptedException {
        Thread.sleep(1000);

        List<WebElement> content_list= driver.findElements(Capture_Text);
        String contents="";
        Thread.sleep(1000);
        for(int i=0;i<content_list.size();i++)
        {
            Thread.sleep(1000);
            contents+=content_list.get(i).getText()+"\n";
        }
        System.out.println(contents);
        logger.info("Text Captured");
        screenshot.take_screenshot("Contents");
    }


    @Test(priority = 4)
    public void openNewTab () throws InterruptedException {
        Thread.sleep(1000);
        driver.switchTo().parentFrame();

        //scroll up
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-1100)", "");

        //click on open new tab
        WebElement click_open_new = driver.findElement(Open_new_tab);
        Actions actions = new Actions(driver);
        actions.moveToElement(click_open_new).click().perform();
        screenshot.take_screenshot("New Tab");
        logger.info("New Tab opened");

        //Close msg prompt
        logger.info("Closing msg prompt");
        WebElement close_msg = driver.findElement(Close_Msg);
        close_msg.click();

        //Click here
        driver.findElement(Click_here).click();
    }

    @Test(priority = 5)
    public void dragAndDrop () throws InterruptedException {

        ArrayList<String> tab_list = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tab_list.get(1));


        //click on drag and drop
        WebElement drag_and_drop = driver.findElement(ClickDragandDrop_button);
        Actions actions = new Actions(driver);
        actions.moveToElement(drag_and_drop);
        actions.moveToElement(drag_and_drop).click().perform();


        //to switch to new frame
        WebElement new_frame = driver.findElement(Trash_Frame);
        driver.switchTo().frame(new_frame);

        //trash window
        WebElement trash_item_list = driver.findElement(Trash_item_list);

        //drag and drop process
        for (int i = 2; i < 4; i++) {
            WebElement drag = driver.findElement(By.xpath("//*[@id=\"gallery\"]/li[" + i + "]/img"));
            Actions action = new Actions(driver);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            action.dragAndDropBy(drag, 400, 10).perform();
        }
        screenshot.take_screenshot("Dragged Items");
        driver.switchTo().window(tab_list.get(0));

    }

    @AfterTest()
    public void close_url () {
        driver.close();
    }
}


