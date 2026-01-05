package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ContactUs;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ContactUsData;

public class TC6ContactUsForm extends BaseClass {

    ContactUsData contact;
    HomePage homePage;
    HeaderOfPage header;
    ContactUs contactUsPage;

    @BeforeClass
    public void contactData(){
       contact = new ContactUsData(
               "Tester",
               "tester@gmail.com",
               "Test Subject",
               "This is a custom message");
    }

    @BeforeMethod
    public void initial(){
        homePage = new HomePage(driver);
        header = new HeaderOfPage(driver);
        contactUsPage = new ContactUs(driver);

    }

    @Test
    public void verifyContactUsForm() throws InterruptedException {
        String initialHomeTitle = homePage.getHomePageTitle();
        Assert.assertEquals(initialHomeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is not displayed as expected");

        header.clickContactUs();

        String formTitle = contactUsPage.getHeading();
        Assert.assertEquals(formTitle,ContactUs.EXPECTED_FORM_HEADING, "Form heading is not displayed as expected");

        contactUsPage.setContactData(contact);
        contactUsPage.uploadFile();
        contactUsPage.clickSubmit();
        contactUsPage.acceptAlert();

        String textMessage = contactUsPage.getSuccessMessage();
        Assert.assertEquals(textMessage, ContactUs.EXPECTED_SUCCESS_MESSAGE, "Details are not submitted successfully");

        contactUsPage.clickHomeButton();

        String returnedHomeTitle = homePage.getHomePageTitle();
        Assert.assertEquals(returnedHomeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title mismatch");


    }
}
