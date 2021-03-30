package com.exampl.gwftest.di.ui.main

import androidx.lifecycle.ViewModel
import com.exampl.gwftest.presentation.login.LoginViewModel
import com.exampl.gwftest.di.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindMainVM(viewModel: LoginViewModel): ViewModel

}