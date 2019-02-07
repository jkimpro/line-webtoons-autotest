package com.webtoons.test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OtherPageTest {
	public String beforePage;
	public String defaultColor ="rgba(0, 213, 100, 1)";
	
	public int pageNum;
	
	Actions action = new Actions(Main.driver);
	
	public OtherPageTest(int pageNum) throws Exception 
	{
		this.pageNum = pageNum;
		
		beforePage = Main.driver.getWindowHandle();
		Main.pageHistory.add(beforePage);
		
		WebElement element = Main.driver.findElement(By.className("g_"+Info.names[pageNum]));
		element.click();
		
		detailInfoCheck();
		subNumPageConvertCheck();
		byGenrePanelCheck();
		genreMatchCheck();
		byRatingsPanelCheck();
	}
	
	@Test
	public void detailInfoCheck() throws Exception{
		for(int i =1; i<=10; i+=3)
		{
			WebElement element = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div[2]/ul/li["+i+"]"));
			String genre = element.findElement(By.className("genre")).getText();
			String title = element.findElement(By.className("subj")).getText();
			element.click();
			
			
			WebElement compPageGen = Main.driver.findElement(By.className("genre"));
			WebElement compPageSubj = Main.driver.findElement(By.className("subj"));
	
			String compGenre = compPageGen.getText();
			String compTitle = compPageSubj.getText();
			
			Assert.assertEquals(compGenre, genre);
			Assert.assertEquals(compTitle, title);

			Main.driver.findElement(By.className("m04")).click();							//다시 처음으로
			Main.driver.findElement(By.className("g_"+Info.names[pageNum])).click();
		}
	}
	
	@Test
	public void subNumPageConvertCheck() throws Exception{
		
		for(int i =1; i<=10; i++)
		{
			WebElement onElement = Main.driver.findElement(By.cssSelector("#content > div.cont_box.v2 > div.challenge_cont_area > div.paginate > a:nth-child("+i+")"));
			Assert.assertTrue(onElement.isDisplayed());
			onElement.click();
			
			onElement = Main.driver.findElement(By.cssSelector("#content > div.cont_box.v2 > div.challenge_cont_area > div.paginate > a:nth-child("+i+") > span"));
		
			String color = onElement.getCssValue("color");
			String backgroundColor = onElement.getCssValue("background");
			
			for(int j =10; j>=1; j--)
			{
				if(i==j) {continue;}
				else
				{
					WebElement otherElement = Main.driver.findElement(By.cssSelector("#content > div.cont_box.v2 > div.challenge_cont_area > div.paginate > a:nth-child("+j+")"));		
					
					Assert.assertTrue(otherElement.isDisplayed());
					
					otherElement = Main.driver.findElement(By.cssSelector("#content > div.cont_box.v2 > div.challenge_cont_area > div.paginate > a:nth-child("+j+") > span"));		
					
					String compColor = otherElement.getCssValue("color");
					String compBackgroundColor = otherElement.getCssValue("background");

					otherElement = Main.driver.findElement(By.className("challenge_lst"));
					String compSubjTitle = otherElement.findElement(By.className("subj")).getText();
					
					Assert.assertNotEquals(color,compColor);
					Assert.assertNotEquals(backgroundColor, compBackgroundColor);
				}		
			}
		}
	}
	
	@Test
	public void byGenrePanelCheck() throws Exception{
		//장르 관련한 이벤트 추가하기.
		for(int i =1; i<=5; i++)
		{
			
			WebElement image = Main.driver.findElement(By.cssSelector("#genreRanking > ul > li:nth-child("+i+") > a > div.pic_area > span.thum_skin"));
			WebElement imageNum = Main.driver.findElement(By.cssSelector("#genreRanking > ul > li:nth-child("+i+") > a > div.pic_area > span.num_ranking.ico_n" +i));
			String num = imageNum.getText();
			
			Assert.assertTrue(image.isDisplayed());
			Assert.assertTrue(imageNum.isDisplayed());
			Assert.assertEquals(Integer.parseInt(num), i);
			
			WebElement information = Main.driver.findElement(By.cssSelector("#genreRanking > ul > li:nth-child("+i+") > a > div.info_area"));
			String title = information.findElement(By.className("subj")).getText();
			String author = information.findElement(By.className("author")).getText();
			
			image.click();
			
			WebElement compPageSubj = Main.driver.findElement(By.className("subj"));
			WebElement compPageAuthor = Main.driver.findElement(By.className("author"));
			
			String compTitle = compPageSubj.getText();
			String compAuthor = compPageAuthor.getText();
			
			compAuthor = compAuthor.replace("author info", "");
		
			Assert.assertEquals(compTitle, title);
			Assert.assertEquals(compAuthor, author);
			
			Main.driver.findElement(By.className("m04")).click();
			Main.driver.findElement(By.className("g_"+Info.names[pageNum])).click();
		}

	}
	
	public void genreMatchCheck() throws Exception
	{
		System.out.println("match check");
		
		for(int gen =2; gen<=6; gen++)
		{
			Main.driver.findElement(By.xpath("//*[@id=\"genreRanking\"]/div/div")).click();
			WebElement web = Main.driver.findElement(By.cssSelector("#genreRanking > div > div > ul > li:nth-child("+gen+")"));
			String mainGenre = web.getText();
			web.click();
			
			mainGenre = mainGenre.replace("(selected)", "");
			mainGenre = mainGenre.replace("\n", "");
			
			
			Main.driver.findElement(By.xpath("//*[@id=\"genreRanking\"]/div/div"));
			//#genreRanking > ul > li:nth-child(1) > a > div.pic_area > span
			WebElement image = Main.driver.findElement(By.cssSelector("#genreRanking > ul > li:nth-child(1) > a > div.pic_area > img"));
			WebElement imageNum = Main.driver.findElement(By.cssSelector("#genreRanking > ul > li:nth-child(1) > a > div.pic_area > span"));
			String num = imageNum.getText();
			
			Assert.assertTrue(image.isDisplayed());
			Assert.assertTrue(imageNum.isDisplayed());
			
			WebElement information = Main.driver.findElement(By.cssSelector("#genreRanking > ul > li:nth-child(1) > a > div.info_area"));
			String title = information.findElement(By.className("subj")).getText();
			String author = information.findElement(By.className("author")).getText();
			
			image.click();
			
			WebElement compPageGenre = Main.driver.findElement(By.className("genre"));
			WebElement compPageSubj = Main.driver.findElement(By.className("subj"));
			WebElement compPageAuthor = Main.driver.findElement(By.className("author"));
			
			String compGenre = compPageGenre.getText();
			String compTitle = compPageSubj.getText();
			String compAuthor = compPageAuthor.getText();
			
			compAuthor = compAuthor.replace("author info", "");
			
			if(mainGenre.equals(compGenre)== false)
			{
				WebElement compGenreWithBar = Main.driver.findElement(By.cssSelector("#content > div.cont_box > div.detail_header.challenge > div.info.challenge > p:nth-child(2)"));
				String temp = compGenreWithBar.getText();
				temp = temp.replace("\"", "");
				compGenre = temp;
			}
			
			System.out.println(compGenre + mainGenre);
			
			Assert.assertEquals(compGenre,mainGenre);
			Assert.assertEquals(compTitle, title);
			Assert.assertEquals(compAuthor, author);
			
			Main.driver.findElement(By.className("m04")).click();
			Main.driver.findElement(By.className("g_"+Info.names[pageNum])).click();
		}
		
	}
	
	@Test
	public void byRatingsPanelCheck() throws Exception{
		for(int i =1; i<=5; i++)
		{
			WebElement image = Main.driver.findElement(By.cssSelector("#rateRanking > ul > li:nth-child("+i+") > a > div.pic_area > span.thum_skin"));
			WebElement imageNum = Main.driver.findElement(By.cssSelector("#rateRanking > ul > li:nth-child("+i+") > a > div.pic_area > span.num_ranking.ico_n" +i));
			String num = imageNum.getText();
			
			Assert.assertTrue(image.isDisplayed());
			Assert.assertTrue(imageNum.isDisplayed());
			
			Assert.assertEquals(Integer.parseInt(num), i);
			
			WebElement information = Main.driver.findElement(By.cssSelector("#rateRanking > ul > li:nth-child("+i+") > a > div.info_area"));
			String title = information.findElement(By.className("subj")).getText();
			String author = information.findElement(By.className("author")).getText();
			
			beforePage = Main.driver.getWindowHandle();
			
			Main.pageHistory.add(beforePage);
			
			information.click();
			
			WebElement compPageSubj = Main.driver.findElement(By.className("subj"));
			WebElement compPageAuthor = Main.driver.findElement(By.className("author"));
					
			String compTitle = compPageSubj.getText();
			String compAuthor = compPageAuthor.getText();
			
			compAuthor = compAuthor.replace("author info", "");
					
			Assert.assertEquals(compTitle, title);
			Assert.assertEquals(compAuthor, author);
			
			Main.driver.findElement(By.className("m04")).click();
			Main.driver.findElement(By.className("g_"+Info.names[pageNum])).click();
		}
	}
}
