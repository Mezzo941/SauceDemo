package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @DataProvider(name = "noValidData")
    public Object[][] getData() {
        return new Object[][]{
                {USER, "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {USER, USER, "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(description = "use valid data for login")
    public void ValidDataUserSuccessfulLogin() {
        loginPage.open();
        loginPage.authorization(USER, PASS);
        Assert.assertTrue(catalogPage.isOpened());
    }

    @Test(description = "use data of the blocked user for login")
    public void lockedUserCantLogin() {
        loginPage.open();
        loginPage.authorization(LOCKED_USER, PASS);
        Assert.assertEquals(loginPage.getDynamicError(), "Epic sadface: Sorry, this user has been locked out.");
    }


    @Test(dataProvider = "noValidData", description = "use invalid data for login")
    public void noValidDataInputBreakSuccess(String user, String password, String error) {
        loginPage.open();
        loginPage.authorization(user, password);
        Assert.assertEquals(loginPage.getDynamicError(), error);
    }

}
