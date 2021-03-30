package com.exampl.gwftest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.exampl.gwftest.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

}