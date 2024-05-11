package com.pavelb.mishlohatest.di

import android.app.Application
import androidx.room.Room
import com.pavelb.mishlohatest.data.db.AppDatabase
import com.pavelb.mishlohatest.data.db.RepositoryDao
import com.pavelb.mishlohatest.data.remote.IRepositoriesApi
import com.pavelb.mishlohatest.data.repositories.ILocalRepoListRepository
import com.pavelb.mishlohatest.data.repositories.IRemoteRepoListRepository
import com.pavelb.mishlohatest.data.repositories.LocalRepoListRepository
import com.pavelb.mishlohatest.data.repositories.RemoteRepoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun providesGithubAPI(
        @TrendingRepositoriesAPIClient retrofit: Retrofit
    ): IRepositoriesApi {
        return retrofit.create(IRepositoriesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepoListRepository(
       githubAPI: IRepositoriesApi,
    ): IRemoteRepoListRepository {
        return RemoteRepoListRepository(githubAPI)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: AppDatabase.Callback): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "local_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    @Singleton
    fun provideRepositoryDao(database: AppDatabase): RepositoryDao {
        return database.getRepositoryDao()
    }

    @Provides
    @Singleton
    fun provideLocalRepoListRepository(dao: RepositoryDao): ILocalRepoListRepository {
        return LocalRepoListRepository(dao)
    }


}