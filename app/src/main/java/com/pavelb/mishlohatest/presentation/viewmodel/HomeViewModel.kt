package com.pavelb.mishlohatest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pavelb.mishlohatest.data.entities.Repository
import com.pavelb.mishlohatest.data.repositories.ILocalRepoListRepository
import com.pavelb.mishlohatest.data.repositories.IRemoteRepoListRepository
import com.pavelb.mishlohatest.utils.SpinnerAction
import com.pavelb.mishlohatest.utils.StartDate
import com.pavelb.mishlohatest.utils.getDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepoRepository: IRemoteRepoListRepository,
    private val localRepoRepository: ILocalRepoListRepository
) : ViewModel() {

    private var currentSelection = MutableLiveData(SpinnerAction.DAY)

    val repos: LiveData<PagingData<Repository>> = currentSelection.switchMap { selection ->
        val date = when (selection) {
            SpinnerAction.DAY -> getDateString(StartDate.DAY)
            SpinnerAction.WEEK -> getDateString(StartDate.WEEK)
            SpinnerAction.MONTH -> getDateString(StartDate.MONTH)
            SpinnerAction.FAVORITE -> getDateString(StartDate.MONTH)
        }
        liveData {
            if (selection == (SpinnerAction.FAVORITE)) {
                emitSource(
                    localRepoRepository.getAll()
                )
            } else {
                emitSource(
                    remoteRepoRepository.getGithubRepositories(date = date).cachedIn(viewModelScope)
                )
            }
        }
    }

    fun onSpinnerAction(spinnerAction: SpinnerAction) {
        currentSelection.postValue(spinnerAction)
    }


}
