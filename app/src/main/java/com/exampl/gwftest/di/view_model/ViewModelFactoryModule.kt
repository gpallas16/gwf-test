package com.exampl.gwftest.di.view_model

import androidx.lifecycle.ViewModelProvider
import com.exampl.gwftest.util.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}