package com.coppermobile.mydagger2sample.module;

import android.content.Context;

import com.coppermobile.mydagger2sample.interfaces.ApplicationContext;
import com.coppermobile.mydagger2sample.interfaces.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * class is used to give Application Context
 */
@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    //    @Named("application_context")  //differentiate between application context and activity context using @Named annotation.
    @ApplicationContext     //differentiate b/w application context & activity context using @Qualifier (@ApplicationContext) anno.
    @RandomUserApplicationScope
    @Provides
    Context context() {
        return context.getApplicationContext();
    }
}
