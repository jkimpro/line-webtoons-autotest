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

	@Test
	public void mod1_homeHighLightCheck() throws Exception {

		System.out.println("homeHighLightCheck method in");

		WebElement temp;
		String color;
		String title;

		Main.driver.findElement(By.className("g_home")).click();

		temp = Main.driver.findElement(By.xpath("//*[@class=\"g_home on\"]/a"));
		color = temp.getCssValue("background-color");
		title = temp.getText();

		Assert.assertTrue(temp.isDisplayed());
		Assert.assertEquals(color, Info.defaultColor);
		Assert.assertEquals(title, "HOME");
	}

	@Test
	public void mod2_recommendTitleCheck() throws Exception {

		System.out.println("recommendTitleCheck method in");

		WebElement temp;
		String color;
		String title;

		temp = Main.driver.findElement(By.xpath("//*[@class=\"discover_spot_inner\"]/h3"));
		color = temp.getCssValue("color");
		title = temp.getText();

		Assert.assertTrue(temp.isDisplayed());
		Assert.assertEquals(color, Info.defaultColor);
		Assert.assertEquals(title, "RECOMMENDED TITLES");

	}

	//////////////////////////// 구현에 실패하였습니다.
	//////////////////////////// 죄송합니다./////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
