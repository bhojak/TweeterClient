package com.bhupen.twitterclient.features.timeline;

import android.support.annotation.StringRes;

import com.bhupen.twitterclient.dataTypes.TimelineItem;

import java.util.List;

public interface TimeLineViewInterface {

    void showTimeline(List<TimelineItem> timelineItems);

    void showNoTweets();

    void showMessage(@StringRes int messageResId);
}
