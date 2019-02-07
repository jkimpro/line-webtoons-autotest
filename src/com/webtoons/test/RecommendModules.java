package com.webtoons.test;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecommendModules {

	private String beforePage;
	
	public String defaultColor ="rgba(0, 213, 100, 1)";
	public int checkInd[] = {1,5,3};
	
	Actions action = new Actions(Main.driver);
	
	public RecommendModules() throws Exception
	{
		homeHighLightCheck();	
		recommendTitleCheck();
		
		/////////////////구현실패///////////////////////////////////////////////////////////////////////////////////////
		/*
		 * 할수 있는 방법들을 모두 강구하여 롤러블 이벤트를 처리해보려고 하였지만, 능력이 부족하여 구현에 실패하였습니다.
		 * 진심으로 죄송합니다.
		 * 
		 * */
		//recommendEventCheck();
		//recommendWebtoonCheck();
	}
	@Test
	public void homeHighLightCheck() throws Exception{
		Main.driver.findElement(By.className("g_home")).click();
		
		WebElement temp = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li[1]/a"));
		String color =temp.getCssValue("background-color");
		Assert.assertEquals(color,defaultColor);
	}
	
	@Test
	public void recommendTitleCheck() throws Exception{
		
		WebElement temp = Main.driver.findElement(By.cssSelector("#recommendArea > div > h3"));
		String title = temp.getText();
		String color = temp.getCssValue("color");
		
		Assert.assertEquals(color,defaultColor);
		Assert.assertEquals(title,"RECOMMENDED TITLES");
		
	}
	
	
	
	///구현에 실패하였습니다. 죄송합니다./////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void recommendEventCheck() throws Exception{
		
		//page 이전 버튼
		for(int i =0; i<2; i++)
		{
			
			WebElement temp = Main.driver.findElement(By.cssSelector("#recommendArea > div > div.paging._pageArea > span > span.ico_discover_pg.on"));
			String recommendPageNum = temp.getText();

			WebElement click = Main.driver.findElement(By.className("btn_prev"));

			action.click(click).perform();
			action.click(click).perform();
			
			temp = Main.driver.findElement(By.cssSelector("#recommendArea > div > div.paging._pageArea > span > span.ico_discover_pg.on"));
			String comparePageNum = temp.getText();
			
			
			System.out.println(comparePageNum +" " + recommendPageNum);
			Assert.assertNotEquals(comparePageNum, recommendPageNum);
		}
		
		//page next 버튼
		for(int i =0; i<2; i++)
		{
			
			WebElement temp = Main.driver.findElement(By.cssSelector("#recommendArea > div > div.paging._pageArea > span > span.ico_discover_pg.on"));
			String recommendPageNum = temp.getText();
			
			WebElement click = Main.driver.findElement(By.className("btn_next"));
			
			action.click(click).perform();
			Thread.sleep(1000);
			temp = Main.driver.findElement(By.cssSelector("#recommendArea > div > div.paging._pageArea > span > span.ico_discover_pg.on"));
			
			String comparePageNum = temp.getText();
			System.out.println(comparePageNum +" " + recommendPageNum);
			Assert.assertNotEquals(comparePageNum, recommendPageNum);	
		}
	}
	
	@Test
	public void recommendWebtoonCheck() throws Exception
	{
		for(int i =1; i<=3; i++)
		{
			for(int j =1; j<=5; j++)
			{
				System.out.println("row: " + i + "col:" + j);
				//Thread.sleep(2000);
				
			//	((JavascriptExecutor)Main.driver).executeScript("return jQuery.active == 0");

			//	WebElement temp = Main.driver.findElement(By.cssSelector("#recommendArea > div > div.discover_spot_rolling._rollingArea > div > div:nth-child("+i+") > ul > li:nth-child("+j+")"));
				
			//	WebDriverWait wait = new WebDriverWait(Main.driver, 2);
			//	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("paging _pageArea`")));
				/*
				String genre = temp.findElement(By.className("genre")).getText();
				String subj = temp.findElement(By.className("subj")).getText();
				String author = temp.findElement(By.className("author")).getText();
				
				System.out.println(genre + " " +subj + " " +author);
				beforePage = Main.driver.getWindowHandle();
				
				temp.click();
				
				//페이지 전환
				WebElement compPageGen = Main.driver.findElement(By.className("genre"));
				WebElement compPageSubj = Main.driver.findElement(By.className("subj"));
				WebElement compPageAuthor = Main.driver.findElement(By.className("author"));
						
				String compGenre = compPageGen.getText();
				String compTitle = compPageSubj.getText();
				String compAuthor = compPageAuthor.getText();
				
				compAuthor = compAuthor.replace("author info", "");
				
				System.out.println(compAuthor);
				
				Assert.assertEquals(compGenre, genre);
				Assert.assertEquals(compTitle, subj);
				Assert.assertEquals(compAuthor, author);
				*/
				
				Main.driver.switchTo().window(beforePage);
				//if(compPage.findElement(By.className("bar")))
				
			}
				
		}
				
	}
	

}
