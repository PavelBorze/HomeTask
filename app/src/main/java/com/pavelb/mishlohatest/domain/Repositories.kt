package com.pavelb.mishlohatest.domain

import com.pavelb.mishlohatest.data.entities.Repository

class Repositories {
    var repos:  MutableList<Repository> = mutableListOf()
    var error: Throwable? = null

}
