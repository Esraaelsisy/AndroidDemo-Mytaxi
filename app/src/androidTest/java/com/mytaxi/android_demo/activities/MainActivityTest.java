package com.mytaxi.android_demo.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.helper.JSONReader;
import com.mytaxi.android_demo.screens.DriverProfileScreen;
import com.mytaxi.android_demo.screens.DriverSearchScreen;
import com.mytaxi.android_demo.screens.LoginScreen;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    LoginScreen loginObj = new LoginScreen();
    DriverSearchScreen driverSearchObj = new DriverSearchScreen();
    DriverProfileScreen driverProfileScreen =  new DriverProfileScreen();



    // Launching the app Main Activity
    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Test
    public void mainActivityTest() throws IOException, JSONException {

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

        // Search for a driver name with some search criteria
        driverSearchObj.SearchforDiver("sa" , "Sarah Scott" , mActivityTestRule);

        // Assert the driver name
        driverProfileScreen.AssertDriverName("Sarah Scott");

        // Call the driver
        driverProfileScreen.CallDiver();

        // Return Back to the search screen
        driverProfileScreen.backToSearchScreen();

        // Logout from the app
        driverSearchObj.Logout();
    }

    @Before
    public void Setup()
    {

        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

    }


}
