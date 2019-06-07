package com.coppermobile.mydagger2sample.module;

import android.content.Context;

import androidx.annotation.NonNull;

import com.coppermobile.mydagger2sample.interfaces.ApplicationContext;
import com.coppermobile.mydagger2sample.interfaces.RandomUserApplicationScope;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ContextModule.class)
public class OkHttpClientModule {

    //differentiate between application context and activity context using @Named annotation.
//    @Provides
//    @RandomUserApplicationScope
//    File file(@Named("application_context") Context context) {
//        File cacheFile = new File(context.getCacheDir(), "HttpCache");
//        cacheFile.mkdirs();
//        return cacheFile;
//    }

    //differentiate between application context and activity context using @Qualifier (@ApplicationContext) annotation.
    @RandomUserApplicationScope
    @Provides
    File file(@ApplicationContext Context context) {
        File cacheFile = new File(context.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Provides
    HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.d(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
}
