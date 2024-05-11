package com.pavelb.mishlohatest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pavelb.mishlohatest.data.entities.Repository
import com.pavelb.mishlohatest.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Repository::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()


}