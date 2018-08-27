package com.bhupen.twitterclient.shared.interfaces;

import com.bhupen.twitterclient.dataTypes.TimelineItem;
import com.bhupen.twitterclient.dataTypes.User;

import java.util.List;

import io.reactivex.Observable;

public interface TwitterService {

    Observable<List<TimelineItem>> getTimelineItems();

    Observable<User> getMyDetails();

    Observable<Boolean> sendTweet(String tweetText);

    User getCurrentUser();

    void setCurrentUser(User user);
}
