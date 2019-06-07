package com.coppermobile.mydagger2sample.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
// annotation tells dagger to create single instance, even if <DaggerComponent>.build() is called many times. It will make the dependency work as singleton.
@Retention(RetentionPolicy.CLASS)
public @interface RandomUserApplicationScope {
}
