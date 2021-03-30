package com.exampl.gwftest.di.ui.dashboard

import androidx.lifecycle.ViewModel
import com.exampl.gwftest.di.view_model.ViewModelKey
import com.exampl.gwftest.presentation.dashboard.fragment.main.DashboardMainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardMainFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardMainViewModel::class)
    abstract fun bindDashboardMainViewModel(viewModel: DashboardMainViewModel): ViewModel


}