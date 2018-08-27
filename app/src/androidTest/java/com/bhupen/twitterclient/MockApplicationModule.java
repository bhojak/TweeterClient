package com.bhupen.twitterclient;

import android.app.Application;

import com.bhupen.twitterclient.shared.interfaces.TwitterService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static org.mockito.Mockito.mock;


@Module
public class MockApplicationModule {

    private Application application;

    public MockApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    TwitterService provideTwitterService() {
        return mock(TwitterService.class);
    }

    @Provides
    @Singleton
    Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }
}

