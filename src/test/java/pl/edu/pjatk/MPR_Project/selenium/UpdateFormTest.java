package pl.edu.pjatk.MPR_Project.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class UpdateFormTest {
    WebDriver webDriver;
    @BeforeEach
    public void setUp() {
        this.webDriver = new EdgeDriver();
    }
    @Test
    public void testUpdateForm(){
        UpdateFormPage updateFormPage = new UpdateFormPage(webDriver);
        updateFormPage
                .open()
                .chooseToUpdate()
                .fillInNameInput("name")
                .fillInColorInput("color")
                .submitUpdate();
    }
}
