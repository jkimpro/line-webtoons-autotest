package com.webtoons.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main {

	public static WebDriver driver;
	public static int memoNum;
	public static ArrayList <String> pageHistory = new ArrayList();
	public String page;
	
	@BeforeClass
	public static void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.webtoons.com/en/");	
	}
	@Test
	public void mod01_Login()throws Exception{
	
		driver.findElement(By.className("lk_login")).click();
		driver.findElement(By.id("emailId")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("btnLogIn")).click();	
	}
	
	@Test
	public void mod02_goDiscover()throws Exception{
		driver.findElement(By.className("m04")).click();
	}

	@Test
	public void mod03_upsideModules()throws Exception{
		new UpsideModules();
	}
	
	@Test
	public void mod04_upsideModulesSub()throws Exception{
		new UpsideModulesSub();
	}
	
	@Test
	public void mod05_recommendTitles() throws Exception{
		new RecommendModules();
	}
	
	@Test
	public void mod06_weeklyHotModules() throws Exception{	
		new WeeklyHotModules();
	}
	
	@Test
	public void mod07_popularByGenre() throws Exception{
		new PopularByGenre();
	}

	@Test
	public void mod08_otherPageTest() throws Exception{
	 	for(int i =1; i<=5; i++)
		{
	 		new OtherPageTest(i);
	 	}
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		driver.quit();
	}
}
