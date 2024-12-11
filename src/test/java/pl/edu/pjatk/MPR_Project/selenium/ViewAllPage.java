package pl.edu.pjatk.MPR_Project.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewAllPage {
    WebDriver webDriver;
    @FindBy(tagName = "h1")
    private WebElement header;

    public ViewAllPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getHeaderText(){
        return this.header.getText();
    }
}
