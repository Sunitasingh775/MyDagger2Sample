package com.coppermobile.mydagger2sample.mainactivityfeature;

import com.coppermobile.mydagger2sample.MainActivity;
import com.coppermobile.mydagger2sample.component.RandomUserComponent;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

//    RandomUsersApi getRandomUserService();
//
//    RandomUserAdapter getRandomUserAdapter();

    void injectMainActivity(MainActivity mainActivity);
}
//Activity Scope