package com.mytaxi.android_demo;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.AccessibilityChecks;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import android.support.test.espresso.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mytaxi.android_demo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EspressoTest {



    private String validUserName="crazydog335";
    private String validPassword ="venture";
    private String invalidPassword = "passme123";
    private String searchPattern="sa";
    private String searchName="Sarah Scott";

    @Rule public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);
    @Rule
    public final ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);



  public void enterUserName(String userName){
        onView(withId(R.id.edt_username)).perform(typeText(userName));
    }

    public void enterPassword(String password){
        onView(withId(R.id.edt_password)).perform(typeText(password));
    }

    public void clickLoginButton(){
        onView(withId(R.id.btn_login)).perform(click());
    }
    public void verifyFabDisplay(){
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }
    public void verifyLoginBtnDisplay(){
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }
    public void openNavDrawer() throws Exception{
        onView(withContentDescription("Open navigation drawer")).perform(click());
        sleep(1000);
    }

    public void logOut() throws InterruptedException {
        onView(withText("Logout")).perform(click());
    }

    public void waitFor(int time) throws InterruptedException {
      sleep(time);
    }

    public void searchText(String text){
        onView(withId(R.id.textSearch)).perform(typeText(text),closeSoftKeyboard());
    }
    public void selectByName(String name){
        onView(withText(name)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
    }

    public void clickCallFab(){
        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());
    }

    @Test
    public void login() throws Exception{

       enterUserName(validUserName);
       enterPassword(validPassword);
       clickLoginButton();
       waitFor(1000);
       verifyFabDisplay();
       openNavDrawer();
       logOut();
    }

    @Test
    public void invalidLogin() {
        enterUserName(validUserName);
        enterPassword(invalidPassword);
        clickLoginButton();
        verifyLoginBtnDisplay();
    }

    @Test
    public void searchTest() throws Exception {
     enterUserName(validUserName);
     enterPassword(validPassword);
     clickLoginButton();
     waitFor(1000);
     searchText(searchPattern);
     waitFor(1000);
     selectByName(searchName);
     waitFor(1000);
     verifyFabDisplay();
     clickCallFab();
    }
}
