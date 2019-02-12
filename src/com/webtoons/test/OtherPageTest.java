package com.webtoons.test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OtherPageTest {

	public String beforePage;
	public String defaultColor = "rgba(0, 213, 100, 1)";
	// 각 pageNum 인덱스 확실히 해둘것.

	@Test
	public void mod1_detailInfoCheck() throws Exception {
		System.out.println("detailInfoCheck method in");

		Actions action = new Actions(Main.driver);
		WebElement element, mainGen, mainTitle;
		WebElement compPageGen, compPageSubj;

		String genre, title;
		String compGenre, compTitle;

		String root = "//*[@class=\"challenge_lst NE=a:cll\"]/ul/li[1]/a"; // 첫번째 요소만 확인할 예정임

		// 하나의 장르별 최상단 가장 왼쪽의 웹툰만 테스트 실시
		Main.driver.findElement(By.className("m04")).click(); // 다시 처음으로
		Main.driver.findElement(By.className("g_" + Info.names[1])).click();

		for (int pageNum = 2; pageNum < Info.names.length - 1; pageNum++) {
			element = Main.driver.findElement(By.xpath(root));

			mainGen = element.findElement(By.className("genre"));
			mainTitle = element.findElement(By.className("subj"));

			Assert.assertTrue(element.isDisplayed());
			Assert.assertTrue(mainGen.isDisplayed());
			Assert.assertTrue(mainTitle.isDisplayed());

			genre = mainGen.getText();
			title = mainTitle.getText();
			genre = genre.replace(" ", "");
			title = title.replace(" ", "");

			element.click();

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

			Main.driver.findElement(By.className("m04")).click(); // 다시 처음으로
			Main.driver.findElement(By.className("g_" + Info.names[pageNum])).click();

		}
	}

	@Test
	public void mod2_subNumPageConvertCheck() throws Exception {
		System.out.println("subNumPageConvertCheck method in");

		WebElement onElement, otherElement;
		String color, backgroundColor, title;
		String compColor, compBackgroundColor, compSubjTitle;
		String page = "//*[@class=\"paginate\"]/";

		for (int pageNum = 1; pageNum <= 9; pageNum++) {
			// [@class="paginate"]/a[2]
			onElement = Main.driver.findElement(By.xpath(page + "a[" + pageNum + "]"));
			Assert.assertTrue(onElement.isDisplayed());
			onElement.click();

			onElement = Main.driver.findElement(By.xpath(page + "a[" + pageNum + "]/span"));

			color = onElement.getCssValue("color");
			backgroundColor = onElement.getCssValue("background");
			title = onElement.getText();

			/////////////////////////// 현재 pageNum과 다음장의 pageNum 의 색상 및 문자 비교
			otherElement = Main.driver.findElement(By.xpath(page + "a[" + (pageNum + 1) + "]/span"));
			Assert.assertTrue(otherElement.isDisplayed());

			compColor = otherElement.getCssValue("color");
			compBackgroundColor = otherElement.getCssValue("background");
			compSubjTitle = otherElement.getText();

			Assert.assertNotEquals(color, compColor);
			Assert.assertNotEquals(backgroundColor, compBackgroundColor);
			Assert.assertNotEquals(compSubjTitle, title);
		}
	}

}
