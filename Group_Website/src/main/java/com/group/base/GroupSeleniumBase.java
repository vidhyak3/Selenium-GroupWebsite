package com.group.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.group.Utils.GroupObjectRespositoryProperties;
import com.group.Utils.LoadGroupProperties;

public class GroupSeleniumBase {
	public RemoteWebDriver driver;
	public static LoadGroupProperties gprops;
	public static GroupObjectRespositoryProperties gbProps;
	
	public void waitUrlContains(String ele, WebDriverWait wait) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains(ele));
		}
	public WebElement click(WebElement ele) {
		// TODO Auto-generated method stub
		ele.click();
		return ele;
	}
	public String getText(WebElement ele) {
		// TODO Auto-generated method stub
		return ele.getText();
	}
	public void type(WebElement ele, String data) {
		// TODO Auto-generated method stub
		ele.sendKeys(data);
	}
	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void validateImageSource(WebElement imageElement, String expectedRelativeSrc, String errorMessage) {
	    String baseUrl = "https://master.d19hb1gupmwyub.amplifyapp.com"; 
	    @SuppressWarnings("deprecation")
		String actualSrc = imageElement.getAttribute("src").trim();

	    String expectedFullSrc = baseUrl + expectedRelativeSrc;

	    Assert.assertEquals(actualSrc, expectedFullSrc, errorMessage);
	}
	public WebElement validateExactText(String xpathKey, String expectedText) {
	    try {
	        String xpath = gbProps.get(xpathKey);                           
	        WebElement element = locateWebElement("xpath", xpath);	        
	        String actualText = getText(element).replaceAll("\\s+", " ").trim();	                
	        Assert.assertEquals(actualText, expectedText.trim(), "Text does not match the expected value!");	     
	        System.out.println("Validation successful: The text matches the expected value for key: " + xpathKey);
	    } catch (NoSuchElementException e) {
	        System.err.println("Element not found for XPath key: " + xpathKey);
	        Assert.fail("Element not found for the provided XPath key.");
	    } catch (NullPointerException e) {
	        System.err.println("No XPath found for key: " + xpathKey);
	        Assert.fail("No XPath found in properties for the provided key.");
	    }
		return null;
	}
	public WebElement locateWebElement(String locatorType, String value) {
		switch (locatorType.toLowerCase()) {
		case "id":
			return driver.findElement(By.id(value));
		case "name":
			return driver.findElement(By.name(value));
		case "classname":
			return driver.findElement(By.className(value));
		case "linktext":
			return driver.findElement(By.linkText(value));
		case "partiallinktext":
			return driver.findElement(By.partialLinkText(value));
		case "xpath":
			return driver.findElement(By.xpath(value));
		case "cssselector":
            return driver.findElement(By.cssSelector(value));
		}
		return null;
	}
	public List<WebElement> locateWebElements(String locatorType, String value) {
	    switch (locatorType.toLowerCase()) {
	        case "id":
	            return driver.findElements(By.id(value));
	        case "name":
	            return driver.findElements(By.name(value));
	        case "classname":
	            return driver.findElements(By.className(value));
	        case "linktext":
	            return driver.findElements(By.linkText(value));
	        case "partiallinktext":
	            return driver.findElements(By.partialLinkText(value));
	        case "xpath":
	            return driver.findElements(By.xpath(value));
	        case "cssselector":
	            return driver.findElements(By.cssSelector(value));  // Added CSS Selector case
	    }
	    return null;
	}
	public void validateVideoIsAutoplaying(WebElement videoElement) throws InterruptedException {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    double startTime = (double) js.executeScript("return arguments[0].currentTime;", videoElement);
	    Thread.sleep(2000); // wait for 2 seconds
	    double endTime = (double) js.executeScript("return arguments[0].currentTime;", videoElement);

	    System.out.println("Video start time: " + startTime);
	    System.out.println("Video end time: " + endTime);

	    Assert.assertTrue(endTime > startTime, "❌ Video is not autoplaying!");
	}



}

