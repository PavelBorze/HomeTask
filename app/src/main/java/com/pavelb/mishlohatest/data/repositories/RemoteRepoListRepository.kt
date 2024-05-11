package com.pavelb.mishlohatest.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.pavelb.mishlohatest.data.entities.Repository
import com.pavelb.mishlohatest.data.remote.IRepositoriesApi
import com.pavelb.mishlohatest.data.remote.ReposPagingRemoteDatasource

import javax.inject.Inject

class RemoteRepoListRepository @Inject constructor(
    private val githubRepositoriesAPI: IRepositoriesApi
) : IRemoteRepoListRepository {


    override suspend fun getGithubRepositories(
        date: String
    ): LiveData<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReposPagingRemoteDatasource(githubRepositoriesAPI, date) }
        ).liveData
    }


}