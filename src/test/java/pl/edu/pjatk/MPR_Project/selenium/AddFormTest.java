package pl.edu.pjatk.MPR_Project.selenium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class AddFormTest {
    WebDriver webDriver;
    @BeforeEach
    public void setUp() {
        this.webDriver = new EdgeDriver();
    }
    @Test
    public void testAddForm(){
        AddFormPage addFormPage = new AddFormPage(webDriver);
        addFormPage
                .open()
                .fillInNameInput("name")
                .fillInColorInput("color")
                .submitForm();
    }
}