package com.webtoons.test;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpsideModules {

	private String beforePage;
	private String tmpBackgroundColor;
	private WebElement tmpElement;
	
	Actions action = new Actions(Main.driver);
	
	public UpsideModules() throws Exception
	{
		upsideWordsCheck();
		upsideWordsClick();
		upsideBackToHome();
	}
	@Test
	public void upsideWordsCheck() throws Exception{

		System.out.println("upsideWordscheck method in");
		
		for(int i = 1; i<=6; i++)
		{
			  WebElement element = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li["+(i+1)+"]/a"));
		      String beforeColor = element.getCssValue("color");
		         
		      action.moveToElement(element).perform();
		      
		      element = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li["+(i+1)+"]/a"));
		    
		      String afterColor = element.getCssValue("color");
		      Info.colors.add(afterColor);																						//Info color 업데이트
		      
		      Assert.assertNotEquals(beforeColor,afterColor);
		   
		      
		      WebElement tmp = Main.driver.findElement(By.className("tit"));
		      action.moveToElement(tmp).perform();
		   
		      element = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li["+(i+1)+"]/a"));
		      String unhoveredColor = element.getCssValue("color");
		      Assert.assertEquals(unhoveredColor,beforeColor);   	
		        
		}
		
	}

	@Test
	public void upsideWordsClick() throws Exception{
	
		System.out.println("upsideWordsclick method in");
		
		for(int i =0; i<2; i++)
		{
			for(int j =1; j<7; j++)
			{
				tmpElement = Main.driver.findElement(By.className("g_"+Info.names[j]));
				String title = tmpElement.getText();
				
				Main.driver.findElement(By.className("g_"+Info.names[j])).click();		
				
				WebElement compElement = Main.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/ul/li["+(j+1)+"]/a"));
				String compBackground = compElement.getCssValue("background-color");
				String comptitle = compElement.getText();
				
				
				if(!title.equals("ALL"))
				{
					Assert.assertEquals(compBackground,Info.colors.get(j-1));
				}
				Assert.assertEquals(comptitle, title);
				//컬러비교
			}
		}
		
	}
	
	@Test
	public void upsideBackToHome() throws Exception{
		Main.driver.findElement(By.className("g_"+Info.names[0])).click();		
		//하이라이트 처리 확인
	}
	
}
