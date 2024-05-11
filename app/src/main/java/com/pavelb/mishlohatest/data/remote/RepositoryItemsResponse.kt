package com.pavelb.mishlohatest.data.remote

import com.google.gson.annotations.SerializedName
import com.pavelb.mishlohatest.data.entities.Owner

import com.pavelb.mishlohatest.data.entities.Repository



data class RepositoryItemsResponse(
    @SerializedName("items")
    private val items: List<RepositoryResponse>,

    ){
    fun toRepoList():List<Repository> =
        items.map {
            it.toRepository()
        }.toList()
}

data class RepositoryResponse(
    @SerializedName("id")
    private val id: String,

    @SerializedName("owner")
    private val owner: OwnerResponse,

    @SerializedName("name")
    private val name: String?,

    @SerializedName("full_name")
    private val fullName: String?,

    @SerializedName("description")
    private val description: String?,

    @SerializedName("stargazers_count")
    private val stargazers_count: String?,

    @SerializedName("language")
    private val language: String?,

    @SerializedName("html_url")
    val url: String,

    @SerializedName("forks_count")
    val forks: Int,

    @SerializedName("watchers")
    val watchers: Int,

    @SerializedName("created_at")
    val createDate: String,

    @SerializedName("updated_at")
    val updateDate: String,

    @SerializedName("open_issues")
    val openIssues: Int

    )  {

    fun toRepository(): Repository {
        val ownerEntity = owner.toOwner()

        return Repository(
            id = id,
            name = name ?: "empty",
            ownerName = ownerEntity.login,
            description = description ?: "empty",
            avatarUrl = ownerEntity.avatarUrl,
            stargazersCount = stargazers_count ?: "",
            language = language ?: "",
            forks = forks.toString(),
            watchers = watchers.toString(),
            createDate = createDate,
            updateDate = updateDate,
            openIssues = openIssues.toString(),
            url = url
        )
    }
}

data class OwnerResponse(
    @SerializedName("avatar_url")
    private val avatar_url: String,

    @SerializedName("login")
    private val login: String,

    )  {

    fun toOwner(): Owner =
        Owner(avatar_url, login)
}