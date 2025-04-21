package com.group.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.group.base.GroupSeleniumBase;

public class GroupLandingPage extends GroupSeleniumBase {
	private String baseurl = "https://www.staging.redbangle.com";
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
		validateHeroVideoSection("/creative_agency.mp4", "/img/home/hero-poster.webp", "CREATIVE AGENCY");
		return this;
	}
	public GroupLandingPage VerifyB2BVideo() throws InterruptedException {
		WebElement b2cVideo = driver.findElement(By.xpath("//video[contains(@src, 'play.mp4')]"));
		validateVideoIsAutoplaying(b2cVideo,"");
		return this;
	}
	public GroupLandingPage VerifyB2CVideo() throws InterruptedException {
		WebElement b2cVideo = driver.findElement(By.xpath("//video[contains(@src, 'play.mp4')]"));
		validateVideoIsAutoplaying(b2cVideo,"");
		return this;
	}
	public void validateHeroVideoSection(String expectedSrc, String expectedPoster, String expectedHeadingText) {
		WebElement videoElement = locateWebElement("xpath",gbProps.get("BannerHeadingVideo"));
	    
	    @SuppressWarnings("deprecation")              // Validate 'src' attribute
		String actualSrc = videoElement.getAttribute("src");
	    Assert.assertEquals(actualSrc,baseurl+expectedSrc, "Video src mismatch. Expected to contain:");

	    @SuppressWarnings("deprecation")             // Validate 'poster' attribute
		String actualPoster = videoElement.getAttribute("poster");
	    Assert.assertEquals(actualPoster, baseurl+expectedPoster, "Video poster mismatch. Expected to contain:");

	    WebElement headingElement = driver.findElement(By.xpath("//*[contains(text(),'" + expectedHeadingText + "')]"));
	    Assert.assertTrue(headingElement.isDisplayed(), "Heading not displayed: " + expectedHeadingText);

	    System.out.println("Video section validation passed successfully.");
	}
	public GroupLandingPage BrandIconsInMarquee() {
		try {
	    	String[][] excelData = readExcelData("BrandLogos");
	    	List<WebElement> marqueeImages = driver.findElements(By.cssSelector(".marquee__inner img"));
	        for (int i = 0; i < marqueeImages.size(); i++) {
	            WebElement image = marqueeImages.get(i);
	            String expectedicon = excelData[i][0]; // Column 1: Heading
		        String expectedaltText = excelData[i][1];
	            @SuppressWarnings("deprecation")
				String actualSrc = image.getAttribute("src");
	            @SuppressWarnings("deprecation")
				String actualAlt = image.getAttribute("alt");

	            Assert.assertEquals(actualSrc, getCurrentUrl() + expectedicon,"icon is not matched"+(i+1));
	            Assert.assertEquals(actualAlt,expectedaltText,"alt text is not matched"+(i+1));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return this;
	}
}
