package com.bhupen.twitterclient.features.timeline;

public interface TimeLinePresenterInterface {

    void initialise(TimeLineViewInterface view);

    void refreshTweets();

    void tweet(String tweetText);
}
