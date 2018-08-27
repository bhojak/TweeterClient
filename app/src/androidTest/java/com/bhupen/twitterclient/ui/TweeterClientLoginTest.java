package com.bhupen.twitterclient.ui;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.bhupen.twitterclient.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * These tests use UI Automator as the Twitter authentication mechanism cannot be tested with Espresso
 *
 *
 * Prerequisites:
 *
 * Make sure the test device has the official Twitter app installed
 * Register a Twitter test user so we can log in with that user
 *
 * Also works in an emulator with an ARM CPU (max API 23 for now, see below)
 * Install the Twitter app from: http://www.androiddrawer.com/25966/download-twitter-2-app-apk/
 * Note: the above Twitter app apk (6.16.0) does not work with x86 CPU based emulator images
 *
 * Note: make sure all accessibility services are turned off (e.g.: LastPass) otherwise you get this
 * http://stackoverflow.com/questions/27132799/java-lang-securityexception-permission-denial-getintentsender-when-using-uia
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 16)
public class TweeterClientLoginTest {

    private static final String APP_PACKAGE = "com.bhupen.twitterclient";
    private static final int TIMEOUT = 5000;
    private static final String LOGIN_BUTTON_TEXT = "Log in with Twitter";
    private UiDevice device;


    @Before
    public void startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), TIMEOUT);
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    @Test
    public void loginSuccessful() throws UiObjectNotFoundException {
        UiObject loginButton = device.findObject(new UiSelector().text(LOGIN_BUTTON_TEXT));
        loginButton.clickAndWaitForNewWindow(TIMEOUT);

        // TODO: this fails to find the text on API 24+ (Android 7+) for some reason
        UiObject allowButton = device.findObject(new UiSelector().text("Allow"));
        allowButton.clickAndWaitForNewWindow(TIMEOUT);

        UiObject activityTitle = device.findObject(new UiSelector()
                .text(getInstrumentation().getTargetContext().getString(R.string.title_activity_twitter_feed)));
        assertThat(activityTitle, notNullValue());
    }

    @Test
    public void loginCancelled() throws UiObjectNotFoundException {
        UiObject loginButton = device.findObject(new UiSelector().text(LOGIN_BUTTON_TEXT));
        loginButton.clickAndWaitForNewWindow(TIMEOUT);

        // TODO: this fails to find the text on API 24+ (Android 7+) for some reason
        UiObject allowButton = device.findObject(new UiSelector().text("Cancel"));
        allowButton.clickAndWaitForNewWindow(TIMEOUT);

        loginButton = device.findObject(new UiSelector().text(LOGIN_BUTTON_TEXT));
        assertThat(loginButton, notNullValue());
    }
}
