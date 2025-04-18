package com.group.Testcases;

import org.testng.annotations.Test;
import com.group.base.GroupSpecificMethods;
import com.group.pages.GroupLandingPage;

public class TestGroupLanding extends GroupSpecificMethods{
	
	@Test
	public void runGroupLanding() throws InterruptedException {
		GroupLandingPage glp = new GroupLandingPage(driver);
		glp.HeaderSection().ValidateHeroBannerHeading().VerifyB2BVideo().VerifyB2CVideo();
			
	}

}
