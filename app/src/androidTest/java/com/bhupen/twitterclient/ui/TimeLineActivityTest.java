package com.bhupen.twitterclient.ui;

import android.support.test.runner.AndroidJUnit4;

import com.bhupen.twitterclient.R;
import com.bhupen.twitterclient.dataTypes.TimelineItem;
import com.bhupen.twitterclient.dataTypes.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * UI tests using Espresso
 * <p>
 * I prefer to use a test environment whenever I can, instead of mocking the API.
 * In case of Twitter the API calls have to be mocked.
 * <p>
 * Another difficulty is that log in with Twitter cannot be done with Espresso
 */
@RunWith(AndroidJUnit4.class)
public class TimeLineActivityTest {

    private List<TimelineItem> timelineItems;


    @Test
    public void canSeeTimelineWhenThereAreTweets() {
        onView(withText(R.string.title_activity_twitter_feed))
                .check(matches(isDisplayed()));

        onView(withText("Bob"))
                .check(matches(isDisplayed()));
        onView(withText("@bob"))
                .check(matches(isDisplayed()));
        onView(withText("10m"))
                .check(matches(isDisplayed()));
        onView(withText("hello"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void refreshTweets() {
        onView(withText("Bob"))
                .check(matches(isDisplayed()));
        onView(withText("Richie Rich"))
                .check(doesNotExist());

        //noinspection SpellCheckingInspection
        timelineItems.add(new TimelineItem("5s", "boo", new User("Richie Rich", "richie-rich", "http://ia.media-imdb.com/images/M/MV5BMTA4NjI0NzE2NjNeQTJeQWpwZ15BbWU4MDU0NzMwNjQx._V1_SY317_CR6,0,214,317_AL_.jpg")));

        onView(withId(R.id.swipe_refresh))
                .perform(swipeDown());

        onView(withText("Bob"))
                .check(matches(isDisplayed()));
        onView(withText("Richie Rich"))
                .check(matches(isDisplayed()));
        onView(withText("@richie-rich"))
                .check(matches(isDisplayed()));
        onView(withText("5s"))
                .check(matches(isDisplayed()));
        onView(withText("boo"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void seeNoTweetsMessageWhenThereAreNoTweets() {
        onView(withText(R.string.title_activity_twitter_feed))
                .check(matches(isDisplayed()));

        timelineItems.clear();

        onView(withId(R.id.swipe_refresh))
                .perform(swipeDown());

        onView(withText(R.string.label_no_tweets))
                .check(matches(isDisplayed()));
    }

    @Test
    public void sendTweet() throws InterruptedException {
        onView(withId(R.id.action_tweet))
                .perform(click());

        String tweetText = "My latest unique tweet with #" + System.currentTimeMillis();
        onView(withId(R.id.tweet_text))
                .perform(typeText(tweetText), pressImeActionButton());

        onView(withText(tweetText))
                .check(matches(isDisplayed()));
    }
}

