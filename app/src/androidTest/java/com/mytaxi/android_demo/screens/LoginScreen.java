package com.mytaxi.android_demo.screens;

import android.view.View;

import com.mytaxi.android_demo.R;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class LoginScreen {

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
    private Matcher<View> getLoginErrorMsg ()
    {
        return withId(R.id.snackbar_text) ;
    }

    public void Login (String usernameValue , String passwordValue) {

        onView(Matchers.allOf(getUserNameTxtBox(), isDisplayed())).perform(click(), typeText(usernameValue), closeSoftKeyboard());
        System.out.println(" User enters username : " + usernameValue);
        onView(Matchers.allOf(getPasswordTxtBox(), isDisplayed())).perform(click(), typeText(passwordValue), closeSoftKeyboard());
        System.out.println("User enters password : " + passwordValue);
        onView(Matchers.allOf(getLoginBtn(), isDisplayed())).perform(click());
        System.out.println("User clicks on Login button");

    }


    public void AssertLoginFailedErrorMsg()
    {
        onView(Matchers.allOf(getLoginErrorMsg() , isDisplayed())).check(matches(withText("Login failed")));
        System.out.println("Asserted that 'Login failed' error message is shown");
    }
}
