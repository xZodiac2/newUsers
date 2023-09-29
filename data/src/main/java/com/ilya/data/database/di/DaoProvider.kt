package com.ilya.data.database.di

import android.content.Context
import com.ilya.data.database.UsersDao
import com.ilya.data.database.db.UsersApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object DaoProvider {
    @Provides
    fun provideDao(@ApplicationContext context: Context): UsersDao {
        return UsersApplicationDatabase.getDatabase(context).getDao()
    }
}