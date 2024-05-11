package com.pavelb.mishlohatest.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.pavelb.mishlohatest.data.entities.Repository



interface IRemoteRepoListRepository {

    suspend fun getGithubRepositories( date:String): LiveData<PagingData<Repository>>




}
