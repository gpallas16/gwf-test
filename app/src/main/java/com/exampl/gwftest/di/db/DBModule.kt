package com.exampl.gwftest.di.db

import com.exampl.gwftest.di.db.module.DataSourceModule
import com.exampl.gwftest.di.db.module.MapperModule
import com.exampl.gwftest.di.db.module.RepositoryModule
import com.exampl.gwftest.di.db.module.RoomDatabaseModule
import dagger.Module

@Module(
    includes = [
        DataSourceModule::class,
        MapperModule::class,
        RepositoryModule::class,
        RoomDatabaseModule::class
    ]
)
object DBModule