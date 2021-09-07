package Pages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;


public class Screenshot {

    public void take_screenshot(String img_name)
    {
        Logger logger = Logger.getLogger("screenshot.class");

        try
        {
            BufferedImage img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(img, "png", new File("C:\\Users\\shlovan\\IdeaProjects\\SeleniumMainAssignment\\src\\test\\Captures\\"+img_name+".png"));
        }
        catch(Exception e)
        {
            logger.info("Unable to capture screenshot");
        }
    }


}


