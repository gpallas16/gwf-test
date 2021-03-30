package com.exampl.gwftest.di

import com.exampl.gwftest.di.ui.dashboard.DashboardModule
import com.exampl.gwftest.di.ui.dashboard.DashboardScope
import com.exampl.gwftest.di.ui.main.MainModule
import com.exampl.gwftest.di.ui.main.MainScope
import com.exampl.gwftest.presentation.dashboard.DashboardActivity
import com.exampl.gwftest.presentation.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @DashboardScope
    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun contributeDashboardActivity(): DashboardActivity

}