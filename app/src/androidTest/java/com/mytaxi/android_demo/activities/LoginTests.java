package com.mytaxi.android_demo.activities;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.helper.JSONReader;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTests extends TestBase{

    @Test
    public  void userEntersWrongCredentials()
    {
        loginObj.Login("esraa", "123456");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.d("Exception : ", e.getMessage());
        }

        loginObj.AssertLoginFailedErrorMsg();
    }

    @Test
    public  void userCanLoginSuccessfully() throws IOException, JSONException {
        // Reading username and password from JSON File
        String sURL = "https://randomuser.me/api/?seed=a1f30d446f820665";
        String usernameValue = JSONReader.readJSONElementFromURL(sURL , "username");
        String passwordValue = JSONReader.readJSONElementFromURL(sURL , "password");

        // Login with Username and password
        loginObj.Login(usernameValue, passwordValue);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.d("Exception : ", e.getMessage());
        }
        // Assert the Search Screen is shown
        driverSearchObj.AssertSearchHeaderText();

        //Logout from the logged in user
        driverSearchObj.Logout();
    }
}
