package com.bhupen.twitterclient;

import com.bhupen.twitterclient.shared.di.components.AppComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockApplicationModule.class})
interface MockApplicationComponent extends AppComponent {
}
