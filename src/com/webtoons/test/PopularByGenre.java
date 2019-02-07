package com.webtoons.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PopularByGenre {
	public String tmpBackgroundColor;
	public String defaultColor ="rgba(0, 213, 100, 1)";
	
	Actions action = new Actions(Main.driver);
	
	public PopularByGenre() throws Exception
	{
		titleCheck();
		titleGenreColorCheck();
		detailsCheck();
	}
	
	@Test
	public void titleCheck() throws Exception{
		WebElement element = Main.driver.findElement(By.className("popular_genre_area"));
		element = element.findElement(By.className("tit"));
		
		String title = element.getText();
		Assert.assertEquals(title, "Popular By Genre|");
		System.out.println("title checked");
	}
	
	
	@Test
	public void titleGenreColorCheck() throws Exception
	{
		for(int i =2; i<=7; i++)
		{
			
			WebElement element = Main.driver.findElement(By.className("popular_genre_area"));	
			element = element.findElement(By.className("g_"+Info.names[i]));	
			
			String originTitle = element.getText();
			System.out.println(originTitle);
			element.click();
		
			WebElement compElement = Main.driver.findElement(By.className("popular_genre_area"));
			compElement = compElement.findElement(By.className("g_"+Info.names[i]));
			
			if(!originTitle.equals("OTHERS"))
			{
				String compBackground = compElement.getCssValue("color");	
				Assert.assertEquals(compBackground, Info.colors.get(i-1));
			}
			
			String comptitle = compElement.getText();
			Assert.assertEquals(comptitle, originTitle);

		}
	}
	
	@Test
	public void detailsCheck() throws Exception{
		//장르 내부 6개 패널 제목 타이틀 체크
		for(int i =2; i<=7; i++) {
			for(int j =1; j<=6; j++)
			{		
				WebElement element = Main.driver.findElement(By.className("popular_genre_area"));
				element = element.findElement(By.className("g_"+Info.names[i]));
				element.click();
			
				WebElement detailElement = Main.driver.findElement(By.cssSelector("#popularByGenreArea > ul:nth-child("+(i+2)+") > li:nth-child("+j+")"));
				
				String genre = detailElement.findElement(By.className("genre")).getText();
				String title = detailElement.findElement(By.className("subj")).getText();
				
				detailElement.click();
			
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
	
}
