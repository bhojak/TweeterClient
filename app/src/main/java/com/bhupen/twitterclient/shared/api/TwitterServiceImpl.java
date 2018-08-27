package com.bhupen.twitterclient.shared.api;

import android.util.Log;

import com.bhupen.twitterclient.dataTypes.TimelineItem;
import com.bhupen.twitterclient.dataTypes.User;
import com.bhupen.twitterclient.shared.interfaces.TwitterService;
import com.bhupen.twitterclient.shared.utils.TimelineConverter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import org.joda.time.DateTime;

import java.util.List;

import io.reactivex.Observable;

public class TwitterServiceImpl extends TwitterApiClient implements TwitterService {

    private static final String TAG = TwitterServiceImpl.class.getSimpleName();

    private User currentUser;

    public TwitterServiceImpl(TwitterSession session) {
        super(session);
    }

    @Override
    public Observable<List<TimelineItem>> getTimelineItems() {
        return Observable.create(subscriber -> {
            Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
                @Override
                public void success(Result<List<Tweet>> result) {
                    Log.i(TAG, "Got the tweets, buddy!");
                    subscriber.onNext(TimelineConverter.fromTweets(result.data, DateTime.now()));
                }

                @Override
                public void failure(TwitterException e) {
                    Log.e(TAG, e.getMessage(), e);
                    subscriber.onError(e);
                }
            };

            getStatusesService().homeTimeline(null, null, null, null, null, null, null).enqueue(callback);
        });
    }

    @Override
    public Observable<User> getMyDetails() {
        return Observable.create(subscriber -> {
            Callback<User> callback = new Callback<User>() {
                @Override
                public void success(Result<User> result) {
                    Log.i(TAG, "Got your details, pal!");
                    subscriber.onNext(new User(result.data.getName(), result.data.getScreenName(), result.data.getProfileImageUrl()));
                }

                @Override
                public void failure(TwitterException e) {
                    Log.e(TAG, e.getMessage(), e);
                    subscriber.onError(e);
                }
            };

            getService(ApiService.class).show(TwitterCore.getInstance().getSessionManager().getActiveSession().getUserId()).enqueue(callback);
        });
}

    @Override
    public Observable<Boolean> sendTweet(String tweetText) {
        return Observable.create(subscriber -> {
            Callback<Tweet> callback = new Callback<Tweet>() {
                @Override
                public void success(Result<Tweet> result) {
                    Log.i(TAG, "Tweet tweeted");
                    subscriber.onNext(true);
                }

                @Override
                public void failure(TwitterException e) {
                    Log.e(TAG, e.getMessage(), e);
                    subscriber.onError(e);
                }
            };

            getStatusesService().update(tweetText, null, null, null, null, null, null, null, null).enqueue(callback);
        });
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void setCurrentUser(User user) {

    }
}
