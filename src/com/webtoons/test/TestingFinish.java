package com.webtoons.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestingFinish {

	@Test
	public static void finish() throws Exception {
		System.out.println("finish method in");
		Main.driver.close();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tearDown method in");
		Main.driver.quit();
	}
}
