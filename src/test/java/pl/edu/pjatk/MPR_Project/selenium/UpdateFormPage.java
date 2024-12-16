package pl.edu.pjatk.MPR_Project.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;

public class UpdateFormPage {
    WebDriver webDriver;
    @FindBy(id = "name")
    WebElement nameInput;
    @FindBy(id = "color")
    WebElement colorInput;
    @FindBy(id = "submit")
    WebElement submitUpdate;



    public UpdateFormPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public UpdateFormPage open(){
        this.webDriver.get("http://localhost:8080/view/all");
        return this;
    }
    public UpdateFormPage chooseToUpdate(){
        WebElement link = webDriver.findElement(By.xpath("//tr[td[text()='2']]/td/a[@id='updateButton']"));
        link.click();
        return this;
    }
    public UpdateFormPage fillInNameInput(String string) {
        this.nameInput.clear();
        this.nameInput.sendKeys(string);
        return this;
    }
    public UpdateFormPage fillInColorInput(String string) {
        this.colorInput.clear();
        this.colorInput.sendKeys(string);
        return this;
    }
    public UpdateFormPage submitUpdate(){
        this.submitUpdate.click();
        return this;
    }
}
