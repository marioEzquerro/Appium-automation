package com.barify.stepsdef;

import com.barify.setups.Capabilities;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebElement;

public class example extends Capabilities {

    @Before({"@RaraliQATeam"})
    public void setup()throws Exception{
        preparation();
    }
}
