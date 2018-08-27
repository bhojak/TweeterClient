package com.bhupen.twitterclient.features.timeline;

import com.bhupen.twitterclient.R;
import com.bhupen.twitterclient.dataTypes.TimelineItem;
import com.bhupen.twitterclient.shared.interfaces.TwitterService;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;

public class TimeLinePresenterImpl implements TimeLinePresenterInterface {

    private  TwitterService service;
    private  Scheduler scheduler;
    private WeakReference<TimeLineViewInterface> view;
    private List<TimelineItem> timelineItems;

    @Inject
    public TimeLinePresenterImpl(TwitterService service, Scheduler scheduler) {
        this.service = service;
        this.scheduler = scheduler;
    }

    @Override
    public void initialise(TimeLineViewInterface view) {
        this.view = new WeakReference<>(view);
        service.getMyDetails()
                .observeOn(scheduler)
                .subscribe(user -> {
                    service.setCurrentUser(user);
                    refreshTweets();
                });
    }
    @Override
    public void refreshTweets() {
        service.getTimelineItems()
                .observeOn(scheduler)
                .subscribe(timelineItems -> {
                    this.timelineItems = timelineItems;
                    TimeLineViewInterface view = this.view.get();
                    if (view != null) {
                        if (timelineItems.size() > 0) {
                            view.showTimeline(timelineItems);
                        } else {
                            view.showNoTweets();
                        }
                    }
                });
    }
    @Override
    public void tweet(String tweetText) {
        timelineItems.add(0, new TimelineItem("0s", tweetText, service.getCurrentUser()));
        view.get().showTimeline(timelineItems);
        service.sendTweet(tweetText)
                .observeOn(scheduler)
                .subscribe(
                        x -> {
                            TimeLineViewInterface view = this.view.get();
                            if (view != null) {
                                view.showMessage(R.string.alert_tweet_successful);
                            }
                        },
                        e -> {
                            TimeLineViewInterface view = this.view.get();
                            if (view != null) {
                                view.showMessage(R.string.alert_tweet_failed);
                            }
                        });
    }


}
