package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest_TestNG {
	
	WebDriver driver;
	String browser = null;
	String url = null;
	
	@BeforeClass
	public void readConfig() {
		
		
		Properties prop = new Properties();
		//InputStream //BufferedReader// FileReader //Scanner---all these read files
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Used environment: " + url);
			url = prop.getProperty("url");
			System.out.println("Used environment: " + url);
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
		@BeforeMethod
		public void init() {
		
			//driver = new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		
			driver.get("https://techfios.com/billing/");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		}

		@Test (priority=1)
		public void loginTest() {
			
			Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong page!!");
	
			WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
			WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
			WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));
	
			USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");
			PASSWORD_FIELD_ELEMENT.sendKeys("abc123");
			SIGNIN_BUTTON_ELEMENT.click();
		}
		@Test (priority=2)
		public void addCustomerTest() {
			
			By USER_NAME = 	By.id("username");
			By PASS_FIELD = By.id("password");
			By SIGNIN_BUTTON = By.name("login");
			By CUSTOMER_BUTTON= By.xpath("//span[text()='Customers']");
			By ADD_CUSTOMER = By.xpath("//a[text()='Add Customer']");
			By FULL_NAME = 	By.xpath("//input[@id='account']");
			By COMPANY_NAME = By.xpath("//select[@id='cid']");
			By EMAIL_FIELD = By.xpath("//input[@id='email']");
			By PHONE_FIELD = By.xpath("//input[@id='phone']");
			By ADDRESS_FIELD = By.xpath("//input[@name='address']");
			By CITY_FIELD = By.xpath("//input[@name='city']");
			By STATE_NAME = By.xpath("//input[@name='state']");
			By ZIP_FIELD = 	By.xpath("//input[@id='zip']");
			By SUBMIT_BUTTON = By.xpath("//button[@id='submit']");
//			By USER_NAME = 	By.id("username");
//			By USER_NAME = 	By.id("username");
//			By USER_NAME = 	By.id("username");
//			By USER_NAME = 	By.id("username");
			
			//LOGIN DATA
			
			String loginId = "demo@techfios.com";
			String password = "abc123";
			
			//Test Data or Mock Data
			
			String fullName = "Test January";
			String companyName = "Techfios";
			String emailAddress = "testJanuary@gmail.com";
			String phoneNumber = "0000000000";
			
			//login
			Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong page!!");
			driver.findElement(USER_NAME).sendKeys(loginId);
			driver.findElement(PASS_FIELD).sendKeys(password);
			driver.findElement(SUBMIT_BUTTON).click();
			Assert.assertEquals(driver.getTitle(), "Dashboard - iBilling", "Wrong page!!"); 
			
			//add customer
			driver.findElement(CUSTOMER_BUTTON).click();
			driver.findElement(ADD_CUSTOMER).click();
			
			//explicit wait
			//WebDriverWait wait = new WebDriverWait(driver, 5);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CUSTOMER));
			waitForElement(driver, 5, ADD_CUSTOMER);
			driver.findElement(ADD_CUSTOMER).click();
			
			
//			waitForElement(driver, 3, locator);
			//explicit wait
			//wait.until(ExpectedConditions.visibilityOfElementLocated(FULL_NAME));
			
			//generate random number
			Random rnd = new Random();
			int gernerateNum = rnd.nextInt(999);
			
			driver.findElement(FULL_NAME).sendKeys(fullName + gernerateNum);
			
			//dropdown 
			Select sel = new Select(driver.findElement(COMPANY_NAME));
			sel.selectByVisibleText(companyName);
			
			
			
		}
		
		public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
			
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}



		
		@AfterMethod
		public void tearDown() {
			
			driver.close();
			driver.quit();
			
	
	}

		//((https://github.com/techfios-git/October2020TesNG.git))

		
		//https://desktop.github.com/
		
		
		//https://github.com/techfios-git/Jan2021_TestNG_project
}
