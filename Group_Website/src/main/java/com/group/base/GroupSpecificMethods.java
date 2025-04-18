package com.group.base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.group.Utils.GroupObjectRespositoryProperties;
import com.group.Utils.LoadGroupProperties;
import com.group.Utils.ReadGroupExcelData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GroupSpecificMethods extends GroupSeleniumBase{
	public String sheetName = "UpdatedMetatags";
	@BeforeClass
    @Parameters("browser")
    public void startApplication(@Optional("chrome") String browser) {
		gprops = new LoadGroupProperties();
        gbProps = new GroupObjectRespositoryProperties();
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup(); 
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } 
        
        driver.get(gprops.get1("groupStaging"));//group,//GlobalStagingurl
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void postCondition() {
        if (driver != null) {
            driver.quit();
        }
    }
    /*public void reportStep(String stepDesc, String stepStatus) {
		if(stepStatus.equalsIgnoreCase("pass")) {
			test.pass(stepDesc);
		} else if (stepStatus.equalsIgnoreCase("fail")) {
			test.fail(stepDesc);
		}
	}*/
    
    @DataProvider(name = "fetchData")
    public Object[][] fetchData() throws IOException {
        return ReadGroupExcelData.readData(sheetName);
    }



}
