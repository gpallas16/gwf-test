package com.exampl.gwftest.di.ui.dashboard

import androidx.lifecycle.ViewModel
import com.exampl.gwftest.di.view_model.ViewModelKey
import com.exampl.gwftest.presentation.dashboard.DashboardViewModel
import com.exampl.gwftest.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

}