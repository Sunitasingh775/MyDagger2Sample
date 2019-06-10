package com.coppermobile.mydagger2sample.component;

import com.coppermobile.mydagger2sample.interfaces.RandomUserApplicationScope;
import com.coppermobile.mydagger2sample.interfaces.RandomUsersApi;
import com.coppermobile.mydagger2sample.module.PicassoModule;
import com.coppermobile.mydagger2sample.module.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {

    RandomUsersApi getRandomUserService();

    Picasso getPicasso();
}
//Application Scope
