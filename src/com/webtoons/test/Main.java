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

	// xpath 찾을 때 가장 말단 부터
	// 따로 메소드 선언하되 내부에 다 선언하도록
	// 일일이 인덱스로 for no
	// By는 변수로 하는 것이 좋음
	// throw -> 통으로 안될것 같을때에
	// try catch -> 한두번 해도 될 것 같을 때.

	public static WebDriver driver;
	public static int memoNum;
	public static ArrayList<String> pageHistory = new ArrayList();
	public String page;

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.webtoons.com/en/");
	}

	@Test
	public void mod01_Login() throws Exception {

		driver.findElement(By.className("lk_login")).click();
		driver.findElement(By.id("emailId")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("btnLogIn")).click();
	}

	@Test
	public void mod02_goDiscover() throws Exception {
		driver.findElement(By.className("m04")).click();
	}

}
