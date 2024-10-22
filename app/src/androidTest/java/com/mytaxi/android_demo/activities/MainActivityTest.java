package com.mytaxi.android_demo.activities;

import android.support.test.filters.LargeTest;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.helper.JSONReader;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends TestBase{


    @Test
    public void userCanLoginSuccessfullyAndCallDriver() throws IOException, JSONException {

        // Reading username and password from JSON File
        String sURL = "https://randomuser.me/api/?seed=a1f30d446f820665";
        String usernameValue = JSONReader.readJSONElementFromURL(sURL , "username");
        String passwordValue = JSONReader.readJSONElementFromURL(sURL , "password");

        // Login with Username and password
        loginObj.Login(usernameValue, passwordValue);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Exception : "+  e.getMessage());
        }

        // Search for a driver name with some search criteria
        driverSearchObj.SearchForDiver("sa" , "Sarah Scott" , mActivityTestRule);

        // Assert the driver name
        driverProfileScreen.AssertDriverName("Sarah Scott");

        // Call the driver
        driverProfileScreen.CallDiver();

        // Return Back to the search screen
        driverProfileScreen.backToSearchScreen();

        // Logout from the app
        driverSearchObj.Logout();
    }
}
