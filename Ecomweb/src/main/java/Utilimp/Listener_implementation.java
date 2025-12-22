package Utilimp;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener_implementation implements ITestListener{
	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		
		TakesScreenshot ts=(TakesScreenshot)Base_test.sDriver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./src/test/resources/ScreenShot/"+TakeScreen_shot.getName()+".png");
		try {
			FileHandler.copy(src, dest);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
