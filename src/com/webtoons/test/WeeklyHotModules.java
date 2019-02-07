package com.webtoons.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeeklyHotModules {
	public String tmpBackgroundColor;
	public String defaultColor ="rgba(0, 213, 100, 1)";
	
	Actions action = new Actions(Main.driver);
	
	public WeeklyHotModules() throws Exception
	{
		titleCheck();
		titleGenreChange();
	}
	
	@Test
	public void titleCheck() throws Exception{
		
		WebElement element = Main.driver.findElement(By.className("weekly_hot_area"));
		element = element.findElement(By.className("tit"));
		
		String title = element.getText();
		Assert.assertEquals(title, "Weekly HOT");
	}
	
	@Test
	public void titleGenreChange() throws Exception
	{
		
		for(int i =1; i<=6; i++)
		{
			WebElement element = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/div[1]/ul/li["+i+"]/a"));
			
			String genre = element.findElement(By.className("genre")).getText();
			String title = element.findElement(By.className("subj")).getText();
			
			element.click();
			
			//페이지 전환
			WebElement compPageGen = Main.driver.findElement(By.className("genre"));
			WebElement compPageSubj = Main.driver.findElement(By.className("subj"));
	
			String compGenre = compPageGen.getText();
			String compTitle = compPageSubj.getText();
			
			Assert.assertEquals(compGenre, genre);
			Assert.assertEquals(compTitle, title);

			Main.driver.findElement(By.className("m04")).click();
		}
	}
	
	
}
