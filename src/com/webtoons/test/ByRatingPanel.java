package com.webtoons.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ByRatingPanel {

	@Test
	public void mod1_byRatingsPanelCheck() throws Exception {
		System.out.println("byRatingsPanelCheck method in");

		WebElement mainElement;

		WebElement image;
		WebElement imageNum;
		WebElement information;
		WebElement compPageSubj;
		WebElement compPageAuthor;

		String title, author, num;
		String compTitle, compAuthor;

		String root = "//*[@id=\"rateRanking\"]/ul/li";
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
			compAuthor = compPageAuthor.getText();
			compAuthor = compAuthor.replace("author info", "");
			compTitle = compTitle.replace(" ", "");
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
}
