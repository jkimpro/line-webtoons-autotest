package com.webtoons.test;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

//rgba(0, 213, 100, 1)
public class UpsideModulesSub {

	public String beforePage;
	public String tmpBackgroundColor;
	public String defaultColor ="rgba(0, 213, 100, 1)";
	
	Actions action = new Actions(Main.driver);
	
	public UpsideModulesSub() throws Exception
	{
		othersOptionEventCheck();
		othersBackToHome();
	}
	
	@Test
	public void othersOptionEventCheck() throws Exception{

		WebElement tmpElement1;
		WebElement tmpElement2;
		System.out.println("othersOptionHover");
		
		tmpElement1 =Main.driver.findElement(By.xpath("//*[@id=\"othersTab\"]/a")); 
		
		//tmpElement1 =Main.driver.findElement(By.className("g_others").id("othersTab"));
		String beforeColor = tmpElement1.getCssValue("color");
		String beforeTitle = tmpElement1.getText();
		
		action.moveToElement(tmpElement1).build().perform();					
		
		tmpElement1 =Main.driver.findElement(By.xpath("//*[@id=\"othersTab\"]/a")); 
		String afterColor = tmpElement1.getCssValue("color");
		String afterTitle = tmpElement1.getText();
		
		Assert.assertNotEquals(beforeColor, afterColor);
		Assert.assertEquals(afterColor, defaultColor);							
		Assert.assertEquals(afterTitle, beforeTitle);
		
		
		//소팅 레이아웃내부
		for(int i =1; i<=17; i++)
		{
			tmpElement1 = Main.driver.findElement(By.xpath("//*[@id=\"othersGenreLayer\"]/li["+i+"]/a"));
			beforeColor = tmpElement1.getCssValue("color");
			beforeTitle = tmpElement1.getText();
			
			action.moveToElement(tmpElement1).perform();
			
			tmpElement2 =Main.driver.findElement(By.xpath("//*[@id=\"othersGenreLayer\"]/li["+i+"]/a")); 
			afterColor = tmpElement2.getCssValue("color");
			afterTitle = tmpElement2.getText();
			//*[@id="othersGenreLayer"]/li[13]/a
			
			Assert.assertNotEquals(beforeColor, afterColor);
			Assert.assertEquals(afterColor, defaultColor);	
			Assert.assertEquals(afterTitle, beforeTitle);
		}
	}


	@Test
	public void othersBackToHome() throws Exception{
		
		//다른 환경 접속
		Main.driver.findElement(By.className("g_drama")).click();
		
		//WebElement home = Main.driver.findElement(By.className("g_home").tagName("a"));
		WebElement home = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li[1]/a"));
		String homeColor = home.getCssValue("color");
		String homeTitle = home.getText();
		
		
		action.moveToElement(home).perform();
		
		home = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li[1]/a"));
		String hoverColor = home.getCssValue("color");
		String hoverTitle = home.getText();
		
		home.click();
		
		home = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li[1]/a"));
		String afterColor = home.getCssValue("color");
		String afterTitle = home.getText();	
		
		Assert.assertNotEquals(hoverColor, homeColor); 	//글자색달라짐 
		Assert.assertEquals(hoverTitle, homeTitle);		//글자는 그대로
		
		Assert.assertNotEquals(hoverColor, afterColor); 	//글자색달라짐 
		Assert.assertEquals(afterTitle,homeTitle);		//글자는 그대로
	}
	
}
