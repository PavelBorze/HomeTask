package com.pavelb.mishlohatest.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pavelb.mishlohatest.data.entities.Repository
import com.pavelb.mishlohatest.utils.makeDateQueryString
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1



class ReposPagingRemoteDatasource(
    private val githubRepositoriesAPI: IRepositoriesApi,
    private val date: String
): PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val repos = githubRepositoriesAPI.searchRepositories(page = position, created = makeDateQueryString(date)).toRepoList()
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}