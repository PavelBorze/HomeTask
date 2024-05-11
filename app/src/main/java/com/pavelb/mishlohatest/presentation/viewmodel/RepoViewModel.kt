package com.pavelb.mishlohatest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavelb.mishlohatest.data.entities.Repository
import com.pavelb.mishlohatest.data.repositories.ILocalRepoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(private val localRepoRepository: ILocalRepoListRepository): ViewModel(){

    val isInFavorites = MutableLiveData(false)

    //we should be using some checking here to see if the repo is already in the database
    fun onAddRepoClicked(repo: Repository) {
        viewModelScope.launch {
            localRepoRepository.insert(repo)
        }
    }


    fun checkIfInFavorites(id: String){
        viewModelScope.launch {
            isInFavorites.postValue(localRepoRepository.exists(id))
        }
    }

}