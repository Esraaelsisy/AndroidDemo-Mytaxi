package com.mytaxi.android_demo.screens;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class DriverSearchScreen {

    private String Tag = "Driver Search Screen : ";

    private Matcher<View> getSearchAutoComplete()
    {
        return withId(R.id.textSearch) ;
    }

    private  Matcher<View> getSideMenu()
    {
        return withContentDescription("Open navigation drawer");

    }

    private  Matcher<View> getLogoutLink()
    {
        return withId(R.id.design_menu_item_text) ;
    }


    public void SearchforDiver(String searchCriteria , String driverName , ActivityTestRule<MainActivity> mActivityTestRule )

    {
        onView(Matchers.allOf(getSearchAutoComplete(),isDisplayed())).perform (click() ,typeText(searchCriteria),closeSoftKeyboard());
        Log.d(Tag , "User enters " + searchCriteria + "in Search Text Box");
        onView(withText(driverName)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());
        Log.d(Tag , "User Clicks on " + driverName + " from Search results");

    }

    public void Logout()
    {
        onView(Matchers.allOf(getSideMenu() , isDisplayed())).perform(click());
        Log.d(Tag , "User clicks on side menu");
        onView(Matchers.allOf(getLogoutLink(), isDisplayed())).perform(click());
        Log.d(Tag , "user clicks on Logout");

    }

}
