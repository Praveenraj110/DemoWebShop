package categoryVerification;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Utilimp.Base_test;
import Utilimp.ReportUtil;
import Utilimp.registerdataUtil;

public class EcomTest extends Base_test{
	@Test(dataProvider="LoginTestdata",dataProviderClass = registerdataUtil.class)
	public void CategoryVerification(Map<String, String> userData) throws InterruptedException, IOException {
		
		 List<WebElement> Category=driver.findElements(By.className("header-menu"));
	        for(WebElement wb:Category) {
	        	Reporter.log("CATEGORY LISTED:"+ wb.getText(),true);
                ReportUtil.write("===== TEST EXECUTION STARTED =====");
                ReportUtil.write("CATEGORY LISTED:"+ wb.getText());


	        }
	        WebElement selectedproduct=Category.get(new Random().nextInt(Category.size()));
	        selectedproduct.click();
	        String selectedCategoryName=driver.findElement(By.xpath("(//a[contains(text(),'Apparel & Shoes')])[1]")).getText();
	        Reporter.log("SELECTED PRODUCT :" +selectedCategoryName,true);
            ReportUtil.write("SELECTED PRODUCT :" +selectedCategoryName);

	        String title = driver.getTitle();
	        ast.assertTrue(title.contains("Demo Web Shop. Apparel & Shoes"),"Page title does not contain category name");
	        
	        
	        List<WebElement> products=driver.findElements(By.className("item-box"));
	        int productcount=products.size();
	        Reporter.log("SUBCATEGORY LISTED",true);
	        ReportUtil.write("product count in selected category:" +productcount);
	        for(WebElement sub:products) {
	        	 ast.assertTrue(productcount>=4,"Failed: EXcepted at least 4 product,but found "+productcount);
	        	 Reporter.log("-"+ sub.getText(),true);
	        	 ReportUtil.write("-"+ sub.getText());
	        	
	        }
	        
	        int total = products.size();
	        ast.assertTrue(total >= 2, "Not enough products!");
	  
	        Random r = new Random();
	        Set<String> productLinks = new HashSet<>();
	        
	        while (productLinks.size() < 2) 
	        {
	            int idx = r.nextInt(total);
	            WebElement p = products.get(idx);
	            String link = p.findElement(By.cssSelector("a")).getAttribute("href");
	            productLinks.add(link);
	        }
	        
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        int successCount = 0;
	        int failCount = 0;
	        
	        List<String> addedProducts = new ArrayList<>();
	        List<String> failedProducts = new ArrayList<>();
	        List<String> addedPrices= new ArrayList<>();

           Reporter.log("\n==== Product Selected ====",true);

	        for (String url : productLinks) 
	        {
	        	String name =null;
		        String price =null;

	            driver.get(url); // Open product page safely
	           
	            name = driver.findElement(By.cssSelector("h1[itemprop='name']")).getText().trim();
	            price = driver.findElement(By.className("product-price")).getText().trim();
	            
	            Reporter.log("Name: " + name,true);
	            Reporter.log("Price: " + price,true);
	            Reporter.log("URL: " + url,true);
	            
	            Reporter.log("--------------------------",true);
	            ReportUtil.write("------------------------------------------");
	            
	            ReportUtil.write("Name: " + name);
	            ReportUtil.write("Price: " + price);
	            ReportUtil.write("URL: " + url);

	            
		        List<WebElement> addBtnList =driver.findElements(By.xpath("//input[contains(@id, 'add-to-cart')]"));
	            if(addBtnList.isEmpty()) 
	                {
	            	 failedProducts.add(name);
	                 failCount++;
	                 Reporter.log("OUT OF STOCK ------→ " + name, true);
	                 ReportUtil.write("OUT OF STOCK -----→ " + name);
	                 continue;
	            	}

	                  addBtnList.get(0).click();
	                  driver.findElement(By.className("close")).click();

			         

	            try {
	            	 WebElement notify = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bar-notification")));
	                 String noteText = notify.getText().trim();
	                
	                if (noteText.contains("Out of stock")) {
	                	 failedProducts.add(name);
	                     failCount++;
	                     	              
	                    Reporter.log("OUT OF STOCK -------→ " + name, true);
	                    ReportUtil.write("Skipped: Add to Cart Not Available ------→ " + name);
	                    driver.findElement(By.xpath("//span[@title='Close']")).click();
	                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("bar-notification")));
		            	continue;
	                } 
	                else{
	                	addedProducts.add(name);
	                    addedPrices.add(price);

	                    successCount++;
	                    
	                    Reporter.log("ADD TO CART SUCCESSFULLY -----→ " + name, true);
	                    ReportUtil.write("ADD TO CART SUCCESSFULLY -----→ " + name);

	                }
 	
	            }
	            catch(Exception e) {
	            	e.printStackTrace();
	            } 
	           
	        }
	        
	        Reporter.log("============== SUMMARY ==============", true);
	        Reporter.log("ADD TO CART SUCCESSFULLY → " + successCount + addedProducts, true);
            Reporter.log("FAILED TO ADD CART  → " + failCount + failedProducts, true);
            
          
            WebElement Cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Shopping cart']")));
            Cart.click();
            
            List<WebElement> Cartcount=driver.findElements(By.className("cart-item-row"));
            int count=Cartcount.size();
            if(count== successCount) {
           
            	ast.assertEquals(count, successCount,"Item count mismatch");
            	double calculatedTotal = 0.0;
            	
            	for (int i = 0; i < Cartcount.size(); i++) {

            	    WebElement row = Cartcount.get(i);
            	    String cartProductName = row.findElement(By.className("product-name")).getText().trim();
            	    String Cartproductprice=row.findElement(By.className("product-unit-price")).getText().trim();
            	    String CartProductQty=row.findElement(By.className("qty-input")).getAttribute("value");
            	    int productqty=Integer.parseInt(CartProductQty);
            	    
            	    ast.assertTrue(addedProducts.contains(cartProductName),"Product Name Doesn't Match");
            	    
            	    String expectedPrice = addedPrices.get(addedProducts.indexOf(cartProductName));
            	    double displayedprice=Double.parseDouble(expectedPrice);
            	    
            	    ast.assertEquals(expectedPrice, Cartproductprice,"Product price does not Match");
            	    
            	    ast.assertEquals(productqty, 1,"Product quantity Does Not Match");
            	    
            	    calculatedTotal =calculatedTotal+displayedprice;
            	 
            	    System.out.println("=========================================================");

					
            	}
            	
            	WebElement price =wait.until(ExpectedConditions.visibilityOfElementLocated( By.className("product-price"))); 
            	String ExpectedTotal = price.getText();
            	double displayedTotal = Double.parseDouble(ExpectedTotal);
         	     ast.assertEquals(displayedTotal, calculatedTotal, "❌ TOTAL PRICE mismatch!");
         	   
            	 driver.findElement(By.id("termsofservice")).click();
                 driver.findElement(By.id("checkout")).click();
                 driver.findElement(By.xpath("//input[contains(@class,'register-button')]")).click();
                 String gender = userData.get("Gender");

                 if (gender.equals("Male")) {
                     driver.findElement(By.id("gender-male")).click();
                 }
                 else {
                     driver.findElement(By.id("gender-female")).click();
                 }
                 driver.findElement(By.id("FirstName")).sendKeys(userData.get("FirstName"));
                 driver.findElement(By.id("LastName")).sendKeys(userData.get("LastName"));
                 driver.findElement(By.id("Password")).sendKeys(userData.get("Password"));
                 driver.findElement(By.id("ConfirmPassword")).sendKeys(userData.get("Confirmpassword"));

                 driver.findElement(By.id("register-button")).click();
                 
                 WebElement errorMsg=driver.findElement(By.className("field-validation-error"));
                 if (errorMsg.isDisplayed()) {
                	    ast.fail("Mandatory field validation error displayed");
                	}  
                 ReportUtil.write("Error message appears:" + errorMsg.getText());

            }
            
            else{
            	Reporter.log("the cart was Empty");
            	ReportUtil.write("the cart was Empty");
            }
            ReportUtil.write("=====================================================");
             
	        ast.assertAll();
	    
	}		
		
	}
	



