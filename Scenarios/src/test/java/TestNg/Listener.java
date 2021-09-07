package TestNg;

import Pages.Screenshot;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener extends Screenshot implements ITestListener
{
    public Logger logger = Logger.getLogger(Listener.class.getName());
    Screenshot sc=new Screenshot();

    @Override
    public void onFinish(ITestContext result) {
        logger.info(result.getName()+" Test case finished");
        sc.take_screenshot("onfinish");

    }


    @Override
    public void onTestFailure(ITestResult result) {

        logger.error(result.getName()+" Test case failed");
        sc.take_screenshot("failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error(result.getName()+" Test case skipped");
        sc.take_screenshot("skipped");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getName()+" Test case started");
        sc.take_screenshot("testStarted");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info(result.getName()+" Test case passed");
        sc.take_screenshot("test_success");
    }
}


