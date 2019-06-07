package com.coppermobile.mydagger2sample.module;

import android.content.Context;

import com.coppermobile.mydagger2sample.interfaces.ApplicationContext;
import com.coppermobile.mydagger2sample.interfaces.RandomUserApplicationScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @Provides
    OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }

    //differentiate between application context and activity context using @Named annotation.
//    @RandomUserApplicationScope
//    @Provides
//    Picasso picasso(@Named("application_context") Context context, OkHttp3Downloader okHttp3Downloader) {
//        return new Picasso.Builder(context)
//                .downloader(okHttp3Downloader)
//                .build();
//    }

    //differentiate between application context and activity context using @Qualifier (@ApplicationContext) annotation.
    @RandomUserApplicationScope
    @Provides
    Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }
}
