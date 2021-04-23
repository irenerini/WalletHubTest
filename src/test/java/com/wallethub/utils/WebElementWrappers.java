package com.wallethub.utils;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

/*This file consists of 
 * reusable functions 
 * for WebElements*/

public class WebElementWrappers {

	static Logger log = Logger.getLogger(WebElementWrappers.class);

	static WebDriverWait wait;

	public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
		try {
			wait = new WebDriverWait(driver, 120);
			element = wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			log.info(e.getMessage());
			//Assert.assertTrue(false);
		}
		return element;
	}

	public static void clickbyActionClass(WebDriver driver, WebElement element) {

		Actions action = new Actions(driver);
		waitForElementVisible(driver, element);
		action.moveToElement(element).click().build().perform();
		log.info(element + " is clicked by Action class");
	}

	public static void mouseOverbyActionClass(WebDriver driver, WebElement element) {

		Actions action = new Actions(driver);
		waitForElementVisible(driver, element);
		action.moveToElement(element).build().perform();
		log.info(element + " is mouse over by Action class");
	}

	public static void clickElement(WebElement element) {

		element.click();

		log.info(element + " is clicked");
	}

	public static void enterText(WebElement element, String text) {

		element.clear();

		element.sendKeys(text);

		log.info("Text is entered for " + element);
	}

	public static void scrollToElementCenter(WebDriver driver, WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		} catch (Exception e) {
			log.info(e.getMessage());
			// Assert.assertTrue(false);
		}
	}

	public static void captureScreenshot(WebDriver driver, String stringFileName) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy.MMM.dd.hh.mm.ss").format(new Date());
			FileUtils.copyFile(srcFile,
					new File("Screenshots//Screenshot_" + stringFileName + " " + timestamp + ".jpg"));

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}
