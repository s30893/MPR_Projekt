package pl.edu.pjatk.MPR_Project.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class AddFormPage {
    WebDriver webDriver;
    @FindBy(id = "name")
    WebElement nameInput;
    @FindBy(id = "color")
    WebElement colorInput;
    @FindBy(id = "submit")
    WebElement submitbutton;
    public AddFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public AddFormPage open() {
        this.webDriver.get("http://localhost:8080/addForm");
        return this;
    }
    public AddFormPage fillInNameInput(String string) {
        this.nameInput.sendKeys(string);
        return this;
    }
    public AddFormPage fillInColorInput(String string) {
        this.colorInput.sendKeys(string);
        return this;
    }
    public AddFormPage submitForm(){
        this.submitbutton.click();
        return this;
    }
}