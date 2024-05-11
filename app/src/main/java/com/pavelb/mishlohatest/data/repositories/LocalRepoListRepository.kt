package com.pavelb.mishlohatest.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.pavelb.mishlohatest.data.db.RepositoryDao
import com.pavelb.mishlohatest.data.entities.Repository
import javax.inject.Inject

class LocalRepoListRepository @Inject constructor(private val repositoryDao: RepositoryDao) : ILocalRepoListRepository{

    override fun getAll(): LiveData<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { repositoryDao.getAll() }
        ).liveData
    }

    override suspend fun insert(repo: Repository) {
        return repositoryDao.insert(repo)
    }

    override suspend fun exists(id: String): Boolean {
        return repositoryDao.exists(id)
    }
}