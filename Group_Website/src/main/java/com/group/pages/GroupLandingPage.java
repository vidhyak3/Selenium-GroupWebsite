package com.group.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.group.base.GroupSeleniumBase;

public class GroupLandingPage extends GroupSeleniumBase {
	private String baseurl = "https://master.d19hb1gupmwyub.amplifyapp.com";
	public GroupLandingPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	public GroupLandingPage HeaderSection(){
		WebElement HeaderLogo = locateWebElement("xpath",gbProps.get("headerLogo"));
		validateImageSource(HeaderLogo, "/logo.svg", "logo image source is incorrect");
		return this;	
	}
	public GroupLandingPage clickMenu() throws InterruptedException {
		click(locateWebElement("xpath",gbProps.get("Menubtn")));
		Thread.sleep(3000);
		return this;
	}
	public GroupLandingPage selectMenuOption(String option) throws InterruptedException {
	    clickMenu();
	    switch (option.toLowerCase()) {
	        case "B2C":
	            click(locateWebElement("xpath", gbProps.get("B2C")));
	            break;
	        case "B2B":
	            click(locateWebElement("xpath", gbProps.get("B2B")));
	            break;
	        case "AboutUs":
	            click(locateWebElement("xpath", gbProps.get("AboutUs")));
	            break;
	        default:
	            System.out.println("Invalid menu option: " + option);
	            break;
	    }
		return this;
	}
	public GroupLandingPage ValidateHeroBannerHeading() {
		validateExactText("BannerHeading","WE ARE A TECH-ENABLED");
		validateHeroVideoSection("/img/home/home-1.mp4", "/img/home/hero-poster.webp", "CREATIVE AGENCY");
		return this;
	}
	public GroupLandingPage VerifyB2BVideo() throws InterruptedException {
		WebElement b2cVideo = driver.findElement(By.xpath("//video[contains(@src, 'play.mp4')]"));
		validateVideoIsAutoplaying(b2cVideo);
		return this;
	}
	public GroupLandingPage VerifyB2CVideo() throws InterruptedException {
		WebElement b2cVideo = driver.findElement(By.xpath("//video[contains(@src, 'play.mp4')]"));
		validateVideoIsAutoplaying(b2cVideo);
		return this;
	}
	public void validateHeroVideoSection(String expectedSrc, String expectedPoster, String expectedHeadingText) {
		WebElement videoElement = locateWebElement("xpath",gbProps.get("BannerHeadingVideo"));
	    // Validate 'src' attribute
	    @SuppressWarnings("deprecation")
		String actualSrc = videoElement.getAttribute("src");
	    Assert.assertTrue(actualSrc.contains(baseurl+expectedSrc), "Video src mismatch. Expected to contain: " + expectedSrc + " but was: " + actualSrc);

	    // Validate 'poster' attribute
	    @SuppressWarnings("deprecation")
		String actualPoster = videoElement.getAttribute("poster");
	    Assert.assertTrue(actualPoster.contains(baseurl+expectedPoster), "Video poster mismatch. Expected to contain: " + expectedPoster + " but was: " + actualPoster);

	    // Validate heading text is displayed
	    WebElement headingElement = driver.findElement(By.xpath("//*[contains(text(),'" + expectedHeadingText + "')]"));
	    Assert.assertTrue(headingElement.isDisplayed(), "Heading not displayed: " + expectedHeadingText);

	    System.out.println("✅ Video section validation passed successfully.");
	}

	
	


}
