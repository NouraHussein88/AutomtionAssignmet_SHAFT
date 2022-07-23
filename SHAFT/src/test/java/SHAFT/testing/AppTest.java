package SHAFT.testing;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;



/**
 * Unit test for simple App.
 */
public class AppTest 
{
   WebDriver driver;
    @Test
    public void shouldAnswerWithTrue()
    {
     driver =new DriverFactory().getDriver();
     BrowserActions.navigateToURL(driver, "http://magento-demo.lexiconn.com/");
    }
}
