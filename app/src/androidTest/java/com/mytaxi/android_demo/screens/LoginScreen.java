package com.mytaxi.android_demo.screens;

import android.util.Log;
import android.view.View;

import com.mytaxi.android_demo.R;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class LoginScreen {
    private String LoginTag= "Login Screen : ";

    private Matcher<View> getUserNameTxtBox()
    {
        return withId(R.id.edt_username) ;
    }

    private Matcher<View> getPasswordTxtBox ()
    {
        return withId(R.id.edt_password) ;
    }

    private Matcher<View> getLoginBtn ()
    {
        return withId(R.id.btn_login) ;
    }

    public void Login (String usernameValue , String passwordValue) {

        onView(Matchers.allOf(getUserNameTxtBox(), isDisplayed())).perform(click(), typeText(usernameValue), closeSoftKeyboard());
        Log.d(LoginTag, " User enters username : " + usernameValue);
        onView(Matchers.allOf(getPasswordTxtBox(), isDisplayed())).perform(click(), typeText(passwordValue), closeSoftKeyboard());
        Log.d(LoginTag, "User enters password : " + passwordValue);
        onView(Matchers.allOf(getLoginBtn(), isDisplayed())).perform(click());
        Log.d(LoginTag, "User clicks on Login button");

    }
}
