package TestNg;

import Pages.Scenario1;
import Pages.Scenario2;
import Pages.Scenario3;
import Pages.Scenario4;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.log4j.PropertyConfigurator;
import java.awt.*;
import java.io.IOException;


@Listeners(Listener.class)
public class Testng {


    static WebDriver driver;

    public static void initialSetup()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\shlovan\\Downloads\\chromedriver.exe");
        String log4jPath = System.getProperty("user.dir") + "//log4j.properties";
        //String log4jPath=System.getProperty("C:\\Users\\shlovan\\IdeaProjects\\SeleniumMainAssignment\\log4j.properties");
        PropertyConfigurator.configure(log4jPath);
    }

    @Test(groups = "Regression")
    public void scenario1_call() throws IOException, InterruptedException {


        Scenario1 scenario1=new Scenario1();
        initialSetup();
        scenario1.open_url();
        scenario1.setiFrame();
        scenario1.selectTraining();
        scenario1.hoverMethod();
        scenario1.getContents();
        scenario1.openNewTab();
        scenario1.dragAndDrop();
        scenario1.close_url();


    }
    @Test(groups ="Regression")
    public void scenario2_call() throws InterruptedException, AWTException {

        Scenario2 scenario2=new Scenario2();
        initialSetup();
        scenario2.open_url2();
        scenario2.upload_file();
        scenario2.close_url();

    }


    @Test(groups = "Smoke")
    public void scenario3_call() throws  InterruptedException {

        Scenario3 scenario3=new Scenario3();
        initialSetup();
        scenario3.open_url3();
        scenario3.openGalleryTab();
        scenario3.close_url();
    }


    @Test(groups ="Sanity")
    public void scenario4_call() throws InterruptedException, AWTException {

        Scenario4 scenario4=new Scenario4();
        initialSetup();
        scenario4.open_url4();
        scenario4.move_cursor();
        scenario4.close_window();

    }
}