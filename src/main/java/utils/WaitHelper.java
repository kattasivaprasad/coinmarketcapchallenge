package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {


    WebDriver driver;
    public WaitHelper(WebDriver driverInstance){
        this.driver = driverInstance;
        PageFactory.initElements(driver, this);
    }

    public void waitCondition(WebElement element, int timeOut){
        try{
            WebDriverWait wait = new WebDriverWait(driver,timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch (Exception e){
            System.out.println("Could not find:" +element);
            e.printStackTrace();
        }
    }

    public void driverWait(int timeOut){
        try{
            Thread.sleep(timeOut);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
