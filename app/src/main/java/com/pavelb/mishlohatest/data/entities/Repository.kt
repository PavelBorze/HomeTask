package com.pavelb.mishlohatest.data.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

// we should be using separate RepositoryEntity class but this was omitted for the sake of simplicity
@Parcelize
@Entity(tableName = "repository", primaryKeys = ["id"])
data class Repository(
    val id: String,
    val name: String,
    val ownerName: String,
    val avatarUrl: String,
    val description: String,
    val stargazersCount: String,
    val language: String,
    val forks: String,
    val watchers: String,
    val createDate: String,
    val updateDate: String,
    val openIssues: String,
    val url: String
) : Parcelable