package com.webtoons.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ByGenrePanel {

	@Test
	public void mod1_byGenrePanelCheck() throws Exception {

		System.out.println("byGenrePanelCheck method in");

		WebElement mainElement;

		WebElement image;
		WebElement imageNum;
		WebElement information;
		WebElement compPageSubj;
		WebElement compPageAuthor;

		String title, author, num;
		String compTitle, compAuthor;

		String root = "//*[@id=\"genreRanking\"]/ul/li";
		String takeImg = "/a/div[1]";
		String takeInfo = "/a/div[2]";
		// 장르 관련한 이벤트 추가하기.

		Main.driver.findElement(By.className("m04")).click();
		Main.driver.findElement(By.className("g_" + Info.names[2])).click();

		for (int i = 1; i <= 5; i++) {
			mainElement = Main.driver.findElement(By.xpath(root + "[" + i + "]"));

			image = Main.driver.findElement(By.xpath(root + "[" + i + "]" + takeImg + "/img"));
			imageNum = Main.driver.findElement(By.xpath(root + "[" + i + "]" + takeImg + "/span"));
			num = imageNum.getText();

			Thread.sleep(1000);
			Assert.assertTrue(image.isDisplayed()); // 이미지가 출력되었는지 확인
			Assert.assertTrue(imageNum.isDisplayed()); // 순위가 출력되었는지 확인
			Assert.assertEquals(Integer.parseInt(num), i); // 순위가 정확한지 확인

			information = Main.driver.findElement(By.xpath(root + "[" + i + "]" + takeInfo));
			title = information.findElement(By.className("subj")).getText();
			author = information.findElement(By.className("author")).getText();
			title = title.replace(" ", "");
			author = author.replace(" ", "");

			mainElement.click();

			compPageSubj = Main.driver.findElement(By.className("subj"));
			compPageAuthor = Main.driver.findElement(By.className("author"));

			compTitle = compPageSubj.getText();
			compTitle = compTitle.replace(" ", "");

			compAuthor = compPageAuthor.getText();
			compAuthor = compAuthor.replace("author info", "");
			compAuthor = compAuthor.replace(" ", "");

			Thread.sleep(1000);
			Assert.assertTrue(compPageSubj.isDisplayed());
			Assert.assertTrue(compPageAuthor.isDisplayed());
			Assert.assertEquals(compTitle, title);
			Assert.assertEquals(compAuthor, author);

			Main.driver.findElement(By.className("m04")).click();
			Main.driver.findElement(By.className("g_" + Info.names[2])).click();
		}

	}

	@Test
	public void mod2_genreMatchCheck() throws Exception {
		System.out.println("genreMatchCheck method in");

		WebElement web;
		WebElement image;
		WebElement imageNum;
		WebElement information;

		WebElement compPageGenre, compPageSubj, compPageAuthor;

		String title, author, mainGenre;
		String compGenre, compTitle, compAuthor;

		String root = "//*[@id=\"genreRanking\"]/ul/li";
		String takeImg = "/a/div[1]";
		String takeInfo = "/a/div[2]";

		for (int gen = 1; gen <= 6; gen++) // 1은 ALL인 경우인테 이것을 제외하기 위해서 다음과 같이 2부터 시작 .
		{
			if (gen == 1) {
				continue;
			}

			web = Main.driver.findElement(By.xpath("//*[@class=\"sort_area _filterArea\"]"));
			web.click();
			// *[@id="genreRanking"]/div/div/ul/li[3]/a
			web = Main.driver.findElement(By.xpath("//*[@class=\"sort_area _filterArea\"]/ul/li[" + gen + "]"));
			mainGenre = web.getText();
			web.click();

			mainGenre = mainGenre.replace("(selected)", "");
			mainGenre = mainGenre.replace("\n", "");
			mainGenre = mainGenre.replace(" ", "");

			image = Main.driver.findElement(By.xpath(root + "[1]" + takeImg + "/img"));
			imageNum = Main.driver.findElement(By.xpath(root + "[1]" + takeImg + "/span"));

			Thread.sleep(1000);
			//Assert.assertTrue(image.isDisplayed());
			//Assert.assertTrue(imageNum.isDisplayed());

			information = Main.driver.findElement(By.xpath(root + "[1]" + takeInfo));
			title = information.findElement(By.className("subj")).getText();
			author = information.findElement(By.className("author")).getText();
			title = title.replace(" ", "");
			author = author.replace(" ", "");

			Main.driver.findElement(By.xpath(root + "[1]")).click();

			Thread.sleep(1000);
			compPageGenre = Main.driver.findElement(By.className("genre"));
			compPageSubj = Main.driver.findElement(By.className("subj"));
			compPageAuthor = Main.driver.findElement(By.className("author"));

			Assert.assertTrue(compPageGenre.isDisplayed());
			Assert.assertTrue(compPageSubj.isDisplayed());
			Assert.assertTrue(compPageAuthor.isDisplayed());

			compGenre = compPageGenre.getText();
			compTitle = compPageSubj.getText();
			compAuthor = compPageAuthor.getText();

			compGenre = compGenre.replace(" ", "");
			compTitle = compTitle.replace(" ", "");
			compAuthor = compAuthor.replace("author info", "");
			compAuthor = compAuthor.replace(" ", "");

			if (mainGenre.equals(compGenre) == false) {// *[@id="content"]/div[2]/div[1]/div[1]/p[2]

				WebElement compGenreWithBar = Main.driver.findElement(By.xpath("//*[@class=\"info challenge\"]/p[2]"));
				String temp = compGenreWithBar.getText();
				temp = temp.replace("\"", "");
				compGenre = temp;
			}

			Assert.assertEquals(compGenre, mainGenre);
			Assert.assertEquals(compTitle, title);
			Assert.assertEquals(compAuthor, author);

			Main.driver.findElement(By.className("m04")).click();
			Main.driver.findElement(By.className("g_" + Info.names[2])).click();
		}
	}
}
