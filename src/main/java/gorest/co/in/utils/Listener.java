package gorest.co.in.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Test \"" + result.getName() + "\" Started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test \"" + result.getName() + "\" Finished Successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("Test \"" + result.getName() + "\" Failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("Test \"" + result.getName() + "\" Skipped.");
    }
}
