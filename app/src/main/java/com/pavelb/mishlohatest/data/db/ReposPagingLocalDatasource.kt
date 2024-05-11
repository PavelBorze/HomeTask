package com.pavelb.mishlohatest.data.db

import androidx.paging.PagingSource
import com.pavelb.mishlohatest.data.entities.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReposPagingLocalDatasource(private val repositoryDao: RepositoryDao) {

    suspend fun insert(repo: Repository) = withContext(Dispatchers.IO) {
        repositoryDao.insert(repo)
    }

    fun getAll(): PagingSource<Int, Repository> {
        return repositoryDao.getAll()
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        repositoryDao.deleteAll()
    }
}