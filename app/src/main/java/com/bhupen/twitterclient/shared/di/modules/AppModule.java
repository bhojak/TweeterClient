package com.bhupen.twitterclient.shared.di.modules;

import android.app.Application;
import android.content.Context;

import com.bhupen.twitterclient.shared.api.TwitterServiceImpl;
import com.bhupen.twitterclient.shared.interfaces.TwitterService;
import com.twitter.sdk.android.core.TwitterCore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Bhupen on 27/08/2018.
 */

@Module
public class AppModule {

    private Application application;


    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    TwitterService provideTwitterService() {
        return new TwitterServiceImpl(TwitterCore.getInstance().getSessionManager().getActiveSession());
    }

    @Provides
    @Singleton
    Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }



}

