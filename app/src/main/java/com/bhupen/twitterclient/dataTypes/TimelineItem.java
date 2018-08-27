package com.bhupen.twitterclient.dataTypes;

public class TimelineItem {
    private final String createdAt;
    private final String text;
    private final User user;

    public TimelineItem(String createdAt, String text, User user) {
        this.createdAt = createdAt;
        this.text = text;
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }
}
