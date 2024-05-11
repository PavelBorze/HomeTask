package com.pavelb.mishlohatest.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val avatarUrl: String,
    val login: String,
) : Parcelable