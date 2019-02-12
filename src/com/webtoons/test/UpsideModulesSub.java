package com.webtoons.test;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class UpsideModulesSub {

	public String beforePage;
	public String tmpBackgroundColor;

	@Test
	public void mod1_othersStartEventCheck() throws Exception {
		System.out.println("othersOptionEventCheck method in");

		Actions action = new Actions(Main.driver);

		WebElement tmpElement1;
		WebElement tmpElement2;
		String beforeColor, beforeTitle;
		String afterColor, afterTitle;

		String othersTabRoot = "//*[@id=\"othersTab\"]/a";

		tmpElement1 = Main.driver.findElement(By.xpath(othersTabRoot));
		Assert.assertTrue(tmpElement1.isDisplayed());

		beforeColor = tmpElement1.getCssValue("color");
		beforeTitle = tmpElement1.getText();

		action.moveToElement(tmpElement1).build().perform();

		tmpElement1 = Main.driver.findElement(By.xpath(othersTabRoot));
		Assert.assertTrue(tmpElement1.isDisplayed());

		afterColor = tmpElement1.getCssValue("color");
		afterTitle = tmpElement1.getText();

		System.out.println(beforeColor + " " + afterColor);
		Assert.assertNotEquals(beforeColor, afterColor);

		Assert.assertEquals(afterColor, Info.defaultColor);
		Assert.assertEquals(afterTitle, beforeTitle);
	}

	// 소팅 레이아웃내부
	@Test
	public void mod2_othersOptionEventCheck() throws Exception {
		System.out.println("othersOptionEventCheck method in");
		Actions action = new Actions(Main.driver);

		WebElement tmpElement2;
		String beforeColor, beforeTitle;
		String afterColor, afterTitle;

		String othersTabRoot;

		for (int i = 1; i <= 17; i++) {
			othersTabRoot = "//*[@id=\"othersGenreLayer\"]/li[" + i + "]/a";

			tmpElement2 = Main.driver.findElement(By.xpath(othersTabRoot));
			beforeColor = tmpElement2.getCssValue("color");
			beforeTitle = tmpElement2.getText();

			action.moveToElement(tmpElement2).perform();

			tmpElement2 = Main.driver.findElement(By.xpath(othersTabRoot));
			afterColor = tmpElement2.getCssValue("color");
			afterTitle = tmpElement2.getText();

			Assert.assertTrue(tmpElement2.isDisplayed());
			Assert.assertNotEquals(beforeColor, afterColor);
			Assert.assertEquals(afterColor, Info.defaultColor);
			Assert.assertEquals(afterTitle, beforeTitle);
		}
	}

	@Test // 특정 페이지만 확인한번 해봄
	public void mod3_othersBackToHome() throws Exception {
		System.out.println("othersBackToHome method in");

		Actions action = new Actions(Main.driver);

		WebElement home;
		String homeColor, homeTitle;
		String hoverColor, hoverTitle;
		String afterColor, afterTitle;

		String homeRoot = "//*[@class=\"g_" + Info.names[2] + "\"]/a";
		String insideRoot = "//*[@class=\"g_" + Info.names[2] + " on\"]/a";

		// 다른 환경 접속
		Main.driver.findElement(By.className("g_" + Info.names[0])).click();

		home = Main.driver.findElement(By.xpath(homeRoot));
		homeColor = home.getCssValue("color");
		homeTitle = home.getText();
		Assert.assertTrue(home.isDisplayed());

		action.moveToElement(home).perform();

		home = Main.driver.findElement(By.xpath(homeRoot));
		hoverColor = home.getCssValue("color");
		hoverTitle = home.getText();
		Assert.assertTrue(home.isDisplayed());

		home.click();

		home = Main.driver.findElement(By.xpath(insideRoot));
		afterColor = home.getCssValue("color");
		afterTitle = home.getText();
		Assert.assertTrue(home.isDisplayed());

		Assert.assertNotEquals(hoverColor, homeColor); // 글자색달라짐
		Assert.assertEquals(hoverTitle, homeTitle); // 글자는 그대로

		Assert.assertNotEquals(hoverColor, afterColor); // 글자색달라짐
		Assert.assertEquals(afterTitle, homeTitle); // 글자는 그대로
	}

}
