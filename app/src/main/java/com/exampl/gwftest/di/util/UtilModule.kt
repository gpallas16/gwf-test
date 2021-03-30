package com.exampl.gwftest.di.util

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class UtilModule {

    @Provides
    fun provideGson(): Gson = Gson()

}