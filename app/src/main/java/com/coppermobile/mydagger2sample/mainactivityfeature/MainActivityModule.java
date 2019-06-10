package com.coppermobile.mydagger2sample.mainactivityfeature;

import com.coppermobile.mydagger2sample.adapter.RandomUserAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
class MainActivityModule {

    @MainActivityScope
    @Provides
    RandomUserAdapter randomUserAdapter(Picasso picasso) {
        return new RandomUserAdapter(picasso);
    }
}
