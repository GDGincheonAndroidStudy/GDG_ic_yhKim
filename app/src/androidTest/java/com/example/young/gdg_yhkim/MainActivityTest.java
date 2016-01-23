package com.example.young.gdg_yhkim;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by apple on 2016-01-22.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {


        @Rule
        public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<MainActivity>(MainActivity.class);

        @Test
        public void checkHelloWorldText() {
                //onView(withText("hello world")).check(matches(isDisplayed()));
            onView(withText("seoul")).check(matches(isDisplayed()));
        }
}