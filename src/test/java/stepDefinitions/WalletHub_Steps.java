package stepDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.wallethub.manager.PageObjectManager;
import com.wallethub.manager.TestContext;
import com.wallethub.manager.WebDriverManager;
import com.wallethub.pageobjects.WalletHub_PageObjects;
import com.wallethub.utils.WebElementWrappers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WalletHub_Steps {

	WalletHub_PageObjects walletHubPageObjects;
	WebDriver driver;
	PageObjectManager pageObjectManager;
	WebDriverManager webDriverManager;
	TestContext testContext;
	
	static Logger log = Logger.getLogger(WebElementWrappers.class);


	public WalletHub_Steps(TestContext context) {
		testContext = context;
		walletHubPageObjects = testContext.getPageObjectManager().getWalletHub_PageObjects();

	}

	@Given("Login to the WalletHub website")
	public void login_to_the_WalletHub_website() {
		
			walletHubPageObjects.clickLoginTab();
			walletHubPageObjects.enterUsername();
			walletHubPageObjects.enterPassword();
			walletHubPageObjects.clickBtnLogin();
			log.info("User logged in successfully");
		
	}

	@When("Rate stars as part of review")
	public void rate_stars_as_part_of_review() throws InterruptedException {
		
			walletHubPageObjects.moveToReviewStar();
			walletHubPageObjects.clickReviewStar();
			log.info("User has clicked on review star");

	}

	@Then("Write a review by selecting company")
	public void write_a_review_by_selecting_company() throws InterruptedException {
		
			walletHubPageObjects.clickSelect();
			walletHubPageObjects.selectInsurance();
			walletHubPageObjects.enterReviewText();
			walletHubPageObjects.clickSubmit();
			log.info("User has submitted a review");
			
		
	}

	@Then("Check for the updation of review feed")
	public void check_for_the_updation_of_review_feed() throws InterruptedException {
	
		walletHubPageObjects.getReview();
		walletHubPageObjects.moveToProfileName();
		walletHubPageObjects.clickProfileMenu();
		walletHubPageObjects.getReviewFeed();
		log.info("Review feed was checked for any review submitted");

	}

}
