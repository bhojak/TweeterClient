package com.bhupen.twitterclient;

import android.app.Application;

import com.bhupen.twitterclient.shared.Constants;
import com.bhupen.twitterclient.shared.di.components.AppComponent;
import com.bhupen.twitterclient.shared.di.components.DaggerAppComponent;
import com.bhupen.twitterclient.shared.di.modules.AppModule;
import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import net.danlew.android.joda.JodaTimeAndroid;

import io.fabric.sdk.android.Fabric;

public class TweeterClientApplication  extends Application {


    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);


        JodaTimeAndroid.init(this);



        Fabric.with(this, new Crashlytics());
        Twitter.initialize(new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET)).build());
    }

    protected AppComponent initDagger(TweeterClientApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
