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

	@Test // hover 했을 때와 안했을 때의 color check
	public void mod1_upsideWordsCheck() throws Exception {
		System.out.println("upsideWordscheck method in");
		Actions action = new Actions(Main.driver);

		WebElement element;
		WebElement tmp;
		String root;
		String beforeColor, afterColor, unhoveredColor;

		for (int i = 1; i < 7; i++) {

			root = "//*[@class=\"g_" + Info.names[i] + "\"]/a";

			element = Main.driver.findElement(By.xpath(root));
			Assert.assertTrue(element.isDisplayed());

			beforeColor = element.getCssValue("color");
			action.moveToElement(element).perform();

			element = Main.driver.findElement(By.xpath(root));
			Assert.assertTrue(element.isDisplayed());

			afterColor = element.getCssValue("color");
			Assert.assertNotEquals(beforeColor, afterColor);
			Assert.assertEquals(afterColor, Info.colors[i - 1]);

			tmp = Main.driver.findElement(By.className("tit"));
			Assert.assertTrue(tmp.isDisplayed());

			action.moveToElement(tmp).perform();

			element = Main.driver.findElement(By.xpath(root));
			Assert.assertTrue(element.isDisplayed());

			unhoveredColor = element.getCssValue("color");
			Assert.assertEquals(unhoveredColor, beforeColor);
		}
	}

	@Test // 클릭했을 때의 배경색과 고유색과 같은지 확인
	public void mod2_upsideWordsClick() throws Exception {
		Actions action = new Actions(Main.driver);
		WebElement tmpElement, compElement;
		String title;
		String compBackground, comptitle;
		String compRoot;

		System.out.println("upsideWordsclick method in");

		for (int i = 1; i < 7; i++) {
			compRoot = "//*[@class=\"g_" + Info.names[i] + " on\"]/a";

			tmpElement = Main.driver.findElement(By.className("g_" + Info.names[i]));
			Assert.assertTrue(tmpElement.isDisplayed());
			title = tmpElement.getText();

			Main.driver.findElement(By.className("g_" + Info.names[i])).click();

			compElement = Main.driver.findElement(By.xpath(compRoot));
			Assert.assertTrue(compElement.isDisplayed());

			compBackground = compElement.getCssValue("background-color");
			comptitle = compElement.getText();

			if (!title.equals("ALL")) {
				Assert.assertEquals(compBackground, Info.colors[i - 1]);
			}
			Assert.assertEquals(comptitle, title);
		}
	}

	@Test
	public void mod3_upsideBackToHome() throws Exception {
		System.out.println("upsideBackToHome method in");
		Main.driver.findElement(By.className("g_" + Info.names[0])).click();
	}

}
