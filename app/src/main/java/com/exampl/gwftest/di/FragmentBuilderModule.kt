package com.exampl.gwftest.di

import com.exampl.gwftest.di.ui.dashboard.DashboardDetailsFragmentModule
import com.exampl.gwftest.di.ui.dashboard.DashboardMainFragmentModule
import com.exampl.gwftest.di.ui.dashboard.DashboardModule
import com.exampl.gwftest.presentation.dashboard.fragment.detail.DashboardDetailsFragment
import com.exampl.gwftest.presentation.dashboard.fragment.main.DashboardMainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [DashboardMainFragmentModule::class, DashboardModule::class])
    abstract fun contributeMainFragment(): DashboardMainFragment

    @ContributesAndroidInjector(modules = [DashboardDetailsFragmentModule::class])
    abstract fun contributeDetailsFragment(): DashboardDetailsFragment

}