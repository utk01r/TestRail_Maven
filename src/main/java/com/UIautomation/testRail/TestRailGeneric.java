package com.UIautomation.testRail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestRailGeneric {
	
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() throws InterruptedException{
		
	System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
	driver=new ChromeDriver();
	
	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get("https://nandinisuruchi.testrail.io/index.php?/suites/view/2&group_by=cases:section_id&group_order=asc");

	driver.findElement(By.id("name")).sendKeys("nandinisuruchi101@gmail.com"); 
		 
	driver.findElement(By.id("password")).sendKeys("02iVLvqj60EuXr.mfitr");
	driver.findElement(By.id("button_primary")).click();
	Thread.sleep(3000);
	}

	@Test
	public void create_test_case_on_UI() throws Exception
	{	 
	
		ExcelDataConfig config = new ExcelDataConfig("./testdata/testdata.xlsx");
		int rows = config.getRowCount(0);
	
		System.out.println(rows+ "=rows");
		for(int i = 1; i < rows; i++) {
			
			int cellcnt = config.getColumnCount("sheet0", i);
			
			System.out.println("cell cont ="+cellcnt);
			
			// for(int j = 0; j < cellcnt; j++) {
				  
				String tc_id = config.getData(0, i, 0);
				String tc_step = config.getData(0, i, 1);
				String tc_exp = config.getData(0, i, 2);
				String tc_title = config.getData(0, i, 3);
				String tc_upexp = config.getData(0, i, 4);
				String tc_expstatus = config.getData(0, i, 5);
				System.out.println("tc_id=   "+tc_id+"tc_step=  "+tc_step+"tc_exp=  "+tc_exp+"tc_title=  "+tc_title+"tc_upexp= "+tc_upexp+"tc_expstatus=  "+tc_expstatus);
				
				
				try{
					Thread.sleep(6000);
				driver.findElement(By.xpath("//td[text()='"+tc_id+"']/..//td[3]//a")).click();
				System.out.println("found");
				
				if(tc_expstatus.equalsIgnoreCase("y")) {
					// Thread.sleep(2000);
					// String tcupate=tc_id;
					// System.out.println(tcupate);
					//driver.findElement(By.xpath("//td[text()='"+tcupate+"']/..//td[3]//a")).click();
					driver.findElement(By.xpath("//span[text()='Edit']")).click();
					WebElement wbele = driver.findElement(By.id("custom_expected"));
					((JavascriptExecutor) driver).executeScript( "arguments[0].scrollIntoView();", wbele);
					wbele.clear();
					driver.findElement(By.id("custom_expected")).sendKeys(tc_upexp);
					driver.findElement(By.id("accept")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("//li[@class='header-menu-item header-menu-item-selected']//a[text()='Test Cases']")).click();
					 
				 }
				}
				catch(Exception e){
					System.out.println("not found");
					driver.findElement(By.xpath("//span[text()='Add Case']")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("title")).sendKeys(tc_title);
					
					driver.findElement(By.xpath("//label[text()='Test-caseId												']/..//input")).sendKeys(tc_id);
					driver.findElement(By.id("custom_steps")).sendKeys(tc_step);
					driver.findElement(By.id("custom_expected")).sendKeys(tc_exp);
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("window.scrollBy(0, 500)", "");
					driver.findElement(By.id("accept")).click();
					
				    driver.findElement(By.xpath("//li[@class='header-menu-item header-menu-item-selected']//a[text()='Test Cases']")).click();
					 
				}
	}  
   
    
 

//	@AfterTest
//	public void teardown(){
//		driver.close();
//	}


}
}

