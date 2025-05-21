package com.github.mcgalanes.happtech.core.database.injection

import android.content.Context
import androidx.room.Room
import com.github.mcgalanes.happtech.core.database.DATABASE_FILENAME
import com.github.mcgalanes.happtech.core.database.HapptechDataBase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get<Context>(),
            klass = HapptechDataBase::class.java,
            name = DATABASE_FILENAME,
        ).build()
    }

    single { get<HapptechDataBase>().artObjectLightDao() }
}
