package com.mytaxi.android_demo.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.mytaxi.android_demo.screens.DriverProfileScreen;
import com.mytaxi.android_demo.screens.DriverSearchScreen;
import com.mytaxi.android_demo.screens.LoginScreen;

import org.junit.Before;
import org.junit.Rule;

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.Matchers.not;

public class TestBase {

    protected LoginScreen loginObj = new LoginScreen();
    protected DriverSearchScreen driverSearchObj = new DriverSearchScreen();
    protected DriverProfileScreen driverProfileScreen =  new DriverProfileScreen();

    // Launching the app Main Activity
    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");
    @Before
    public void Setup()
    {
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }
}
