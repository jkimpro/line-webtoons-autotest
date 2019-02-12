package com.webtoons.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeeklyHotModules {

	@Test
	public void mod1_titleCheck() throws Exception {
		System.out.println("titleCheck method in");

		WebElement element;
		String title;

		element = Main.driver.findElement(By.className("weekly_hot_area"));
		element = element.findElement(By.className("tit"));
		title = element.getText();

		Assert.assertTrue(element.isDisplayed());
		Assert.assertEquals(title, "Weekly HOT");
	}

	@Test
	public void mod2_titleGenreChange() throws Exception {

		System.out.println("titleGenreChange method in");

		Actions action = new Actions(Main.driver);

		for (int i = 1; i < 7; i++) {

			WebElement element = Main.driver.findElement(By.xpath("//*[@class=\"discover_lst\"]/li[" + i + "]/a"));
			WebElement compPageGen;
			WebElement compPageSubj;

			String genre;
			String title;
			String compGenre;
			String compTitle;

			genre = element.findElement(By.className("genre")).getText();
			title = element.findElement(By.className("subj")).getText();
			genre = genre.replace(" ", "");
			title = title.replace(" ", "");

			Assert.assertTrue(element.isDisplayed());

			element.click();

			// 페이지 전환
			compPageGen = Main.driver.findElement(By.className("genre"));
			compPageSubj = Main.driver.findElement(By.className("subj"));

			compGenre = compPageGen.getText();
			compTitle = compPageSubj.getText();
			compGenre = compGenre.replace(" ", "");
			compTitle = compTitle.replace(" ", "");

			Assert.assertEquals(compGenre, genre);
			Assert.assertEquals(compTitle, title);

			Main.driver.findElement(By.className("m04")).click(); // 다시 돌아가기.
		}
	}

}
