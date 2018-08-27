package com.bhupen.twitterclient.dataTypes;

import com.google.gson.annotations.SerializedName;

public class TwitterTweet {

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    @SerializedName("in_reply_to_status_id")
    private String inReplyToStatusId;

    @SerializedName("in_reply_to_user_id")
    private String inReplyToUserId;

    @SerializedName("in_reply_to_screen_name")
    private String inReplyToScreenName;

    @SerializedName("user")
    private User twitterUser;

    public TwitterTweet(String createdAt, String id, String text, String inReplyToStatusId, String inReplyToUserId, String inReplyToScreenName, User twitterUser) {
        this.createdAt = createdAt;
        this.id = id;
        this.text = text;
        this.inReplyToStatusId = inReplyToStatusId;
        this.inReplyToUserId = inReplyToUserId;
        this.inReplyToScreenName = inReplyToScreenName;
        this.twitterUser = twitterUser;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public String getInReplyToUserId() {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(String inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    public User getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(User twitterUser) {
        this.twitterUser = twitterUser;
    }
}
