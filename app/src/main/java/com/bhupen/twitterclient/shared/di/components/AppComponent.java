package com.bhupen.twitterclient.shared.di.components;


import android.app.Application;

import com.bhupen.twitterclient.features.timeline.TimeLineActivity;
import com.bhupen.twitterclient.features.timeline.TimeLinePresenterImpl;
import com.bhupen.twitterclient.features.tweeterClient.TweeterClientActivity;
import com.bhupen.twitterclient.features.tweeterClient.TweeterClientPresenterImpl;
import com.bhupen.twitterclient.shared.di.modules.AppModule;
import com.bhupen.twitterclient.shared.di.modules.NetModule;
import com.bhupen.twitterclient.shared.di.modules.PresenterModule;
import com.bhupen.twitterclient.shared.interfaces.TwitterService;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;


@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetModule.class})
public interface AppComponent {


    TwitterService twitterService();

    Scheduler scheduler();
    Application application();

    void inject(TweeterClientActivity target);
    void inject(TimeLineActivity target);
    void inject(TweeterClientPresenterImpl target);
    void inject(TimeLinePresenterImpl target);

}
