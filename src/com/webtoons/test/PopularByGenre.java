package com.webtoons.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PopularByGenre {
	public String tmpBackgroundColor;

	@Test
	public void mod1_titleCheck() throws Exception {
		System.out.println("titleCheck method in");

		Actions action = new Actions(Main.driver);

		WebElement element;
		String title;

		element = Main.driver.findElement(By.className("popular_genre_area"));
		element = element.findElement(By.className("tit"));

		title = element.getText();

		Assert.assertTrue(element.isDisplayed());
		Assert.assertEquals(title, "Popular By Genre|");
	}

	@Test
	public void mod2_titleGenreColorCheck() throws Exception {
		System.out.println("titleGenreColorCheck method in");

		WebElement element;
		WebElement compElement;

		String originTitle;
		String compTitle;

		for (int i = 2; i <= 7; i++) {

			element = Main.driver.findElement(By.className("popular_genre_area"));
			element = element.findElement(By.className("g_" + Info.names[i]));
			Assert.assertTrue(element.isDisplayed());

			originTitle = element.getText();
			element.click();

			compElement = Main.driver.findElement(By.className("popular_genre_area"));
			compElement = compElement.findElement(By.className("g_" + Info.names[i]));
			compTitle = compElement.getText();

			Assert.assertTrue(compElement.isDisplayed());
			Assert.assertEquals(compTitle, originTitle);

			if (!originTitle.equals("OTHERS")) {
				String compBackground = compElement.getCssValue("color");
				Assert.assertEquals(compBackground, Info.colors[i - 1]);
			}
		}
	}

	@Test
	public void mod3_detailsCheck() throws Exception {
		// 장르 내부 6개 패널 제목 타이틀 체크

		System.out.println("detailsCheck method in");

		WebElement element;
		WebElement detailElement;
		WebElement mainPageGen, mainPageSubj;
		WebElement compPageGen, compPageSubj;

		String genre, title;
		String compGenre, compTitle;

		for (int i = 2; i <= 7; i++) {
			for (int j = 1; j <= 6; j++) {
				element = Main.driver.findElement(By.className("popular_genre_area"));
				element = element.findElement(By.className("g_" + Info.names[i]));
				Assert.assertTrue(element.isDisplayed());

				element.click();

				detailElement = Main.driver
						.findElement(By.xpath("//*[@id=\"popularByGenreArea\"]/ul[" + (i) + "]/li[" + j + "]/a"));

				mainPageGen = detailElement.findElement(By.className("genre"));
				mainPageSubj = detailElement.findElement(By.className("subj"));

				genre = mainPageGen.getText();
				title = mainPageSubj.getText();
				genre = genre.replace(" ", "");
				title = title.replace(" ", "");

				Assert.assertTrue(detailElement.isDisplayed());
				Assert.assertTrue(mainPageGen.isDisplayed());
				Assert.assertTrue(mainPageSubj.isDisplayed());

				detailElement.click();

				// 페이지 전환
				compPageGen = Main.driver.findElement(By.className("genre"));
				compPageSubj = Main.driver.findElement(By.className("subj"));

				compGenre = compPageGen.getText();
				compTitle = compPageSubj.getText();
				compGenre = compGenre.replace(" ", "");
				compTitle = compTitle.replace(" ", "");

				Assert.assertTrue(compPageGen.isDisplayed());
				Assert.assertTrue(compPageSubj.isDisplayed());
				Assert.assertEquals(compGenre, genre);
				Assert.assertEquals(compTitle, title);

				Main.driver.findElement(By.className("m04")).click();
			}
		}

	}

}
