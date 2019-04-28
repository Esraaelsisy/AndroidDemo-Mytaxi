package com.mytaxi.android_demo.screens;

import android.view.View;

import com.mytaxi.android_demo.R;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DriverProfileScreen {

    private Matcher<View> getCallBtn()
    {
        return withId(R.id.fab) ;
    }

    private Matcher<View> getDriverName()
    {
        return withId(R.id.textViewDriverName) ;
    }

    public void CallDiver()
    {
        onView(Matchers.allOf(getCallBtn(),isDisplayed())).perform(click());
        System.out.println("User calls the driver");
    }

    public void AssertDriverName(String driverName)
    {
        onView(Matchers.allOf(getDriverName(), isDisplayed())).perform().check(matches(withText(driverName)));
        System.out.println("Asserted that Driver name is "+ driverName);
    }

    public void backToSearchScreen()
    {
        pressBack();
        System.out.println("User pressed Back to Search Screen");
    }




}
