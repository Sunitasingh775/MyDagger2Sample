package com.coppermobile.mydagger2sample.module;

import android.content.Context;

import com.coppermobile.mydagger2sample.interfaces.RandomUserApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * class is used to give Activity Context
 */
@Module
public class ActivityModule {

    private Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Named("activity_context")
    @RandomUserApplicationScope
    @Provides
    Context context() {
        return context;
    }
}
