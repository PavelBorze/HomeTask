package com.pavelb.mishlohatest.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData

import com.pavelb.mishlohatest.data.entities.Repository

interface ILocalRepoListRepository {

    fun getAll(): LiveData<PagingData<Repository>>

    suspend fun insert(repo: Repository)

    suspend fun exists(id: String): Boolean

}