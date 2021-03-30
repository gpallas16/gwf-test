package com.exampl.gwftest.di

import android.app.Application
import com.exampl.gwftest.BaseApplication
import com.exampl.gwftest.di.db.DBModule
import com.exampl.gwftest.di.interactor.InteractorModule
import com.exampl.gwftest.di.network.RetrofitModule
import com.exampl.gwftest.di.util.UtilModule
import com.exampl.gwftest.di.view_model.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelFactoryModule::class,
        DBModule::class,
        InteractorModule::class,
        RetrofitModule::class,
        UtilModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}