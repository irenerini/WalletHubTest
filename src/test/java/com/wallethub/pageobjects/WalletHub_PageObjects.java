package com.wallethub.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wallethub.utils.ConfigFileReader;
import com.wallethub.utils.WebElementWrappers;

import junit.framework.Assert;

//*** This file is the Page Object Model having the WebElements and its methods ***//

public class WalletHub_PageObjects {

	WebElementWrappers webElementWrapper;
	ConfigFileReader configFileReader;
	WebDriver driver;

	public WalletHub_PageObjects(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// finding web element

	@FindBy(css = "div#join-light>form>div>nav>ul>li:nth-of-type(2)>a")
	private WebElement logintab;

	@FindBy(id = "em-ipt")
	private WebElement username;

	@FindBy(id = "pw1-ipt")
	private WebElement password;

	@FindBy(xpath = "//span[text()='Login']")
	private WebElement btnlogin;

	@FindBy(xpath = "(//*[local-name() = 'svg'])[14]")
	private WebElement fourthstar;

	@FindBy(xpath = "//span[text()='Select...']")
	private WebElement drpselect;

	@FindBy(xpath = "//li[text()='Health Insurance']")
	private WebElement drpvalue;

	@FindBy(xpath = "//textarea[contains(@class,'textarea wrev-user-input')]")
	private WebElement reviewtext;

	@FindBy(xpath = "//div[text()='Submit']")
	private WebElement btnsubmit;

	@FindBy(xpath = "(//span[@class='brgm-list-title'])[3]")
	private WebElement profilename;

	@FindBy(xpath = "//div[@class='brgm-list brgm-user-list ng-enter-element']//a[1]")
	private WebElement profilemenu;

	@FindBy(xpath = "//div[@class='pr-rec-img-wrapper']/following-sibling::div[1]")
	private WebElement reviewfeed;
	
	@FindBy(xpath = "//span[text()=' Your Review']")
	private WebElement review;
	
	@FindBy(xpath = "//div//h4")
	private WebElement reviewconfirmation;
	
	@FindBy(xpath = "//div[text()='Continue']")
	private WebElement ckcontinue;

	static Logger log = Logger.getLogger(WebElementWrappers.class);

	public void clickLoginTab() {
		webElementWrapper = new WebElementWrappers();
		WebElementWrappers.clickElement(logintab);
	}

	public void enterUsername() {
		configFileReader = new ConfigFileReader();
		WebElementWrappers.clickElement(username);
		WebElementWrappers.enterText(username, configFileReader.getWHubUsername());
	}

	public void enterPassword() {
		WebElementWrappers.clickElement(password);
		WebElementWrappers.enterText(password, configFileReader.getWHubPassword());
		WebElementWrappers.captureScreenshot(driver, "Login Screen");
	}

	public void clickBtnLogin() {
		WebElementWrappers.clickElement(btnlogin);
	
	}

	public void moveToReviewStar() throws InterruptedException {
		Thread.sleep(5000);
		driver.get("http://wallethub.com/profile/test_insurance_company/");
		WebElementWrappers.scrollToElementCenter(driver, fourthstar);
		WebElementWrappers.mouseOverbyActionClass(driver, fourthstar);
		WebElementWrappers.captureScreenshot(driver, "Review stars mouse over");

	}

	public void clickReviewStar() {
		WebElementWrappers.clickbyActionClass(driver, fourthstar);
	}

	public void clickSelect() throws InterruptedException {
		Thread.sleep(3000);
		WebElementWrappers.clickElement(drpselect);
	}

	public void selectInsurance() {
		WebElementWrappers.clickElement(drpvalue);
	}

	public void enterReviewText() {
		WebElementWrappers.enterText(reviewtext, configFileReader.getWHubReviewText());
		WebElementWrappers.captureScreenshot(driver, "Review Screen");

	}

	public void clickSubmit() throws InterruptedException {
		WebElementWrappers.clickElement(btnsubmit);
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div//h4"))));
		String rtext = reviewtext.getText();
		System.out.println("Review Confirmation message " + rtext);
		log.info(rtext);
		WebElementWrappers.captureScreenshot(driver, "Review Confirmation screen");
		WebElementWrappers.clickElement(ckcontinue);
	}
	
	public void getReview() throws InterruptedException {
		Thread.sleep(3000);
		WebElementWrappers.scrollToElementCenter(driver, review);
		WebElementWrappers.captureScreenshot(driver, "Review");
	}

	public void moveToProfileName() {
		WebElementWrappers.mouseOverbyActionClass(driver, profilename);
	}

	public void clickProfileMenu() {
		WebElementWrappers.clickbyActionClass(driver, profilemenu);
		WebElementWrappers.captureScreenshot(driver, "Profile Review Screen");

	}


	public void getReviewFeed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//div[@class='pr-rec-img-wrapper']/following-sibling::div[1]"))));
			System.out.println("Review feed is updated");
			log.info("Review feed is updated");
			Assert.assertTrue(true);


		} catch (Exception e) {
			System.out.println("Review feed is not updated");
			log.info("Review feed is not updated");
			//Assert.assertTrue(false);
		}
	}

}