package Utilimp;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners(Utilimp.Listener_implementation.class)

public class Base_test {
	public WebDriver driver;
	public static WebDriver sDriver;
	public Actions actions;
	public Random random; 
	public SoftAssert ast;
	public Select sel;
	
	@BeforeClass
	public void launch() {
		driver =new ChromeDriver();
		sDriver=driver;
		random=new Random();
		actions=new Actions(driver);
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        ast=new SoftAssert();
        
        
	}
	@AfterClass
	public void closeBrowser() {
		  driver.quit();
		
	}
	

}
	

	

