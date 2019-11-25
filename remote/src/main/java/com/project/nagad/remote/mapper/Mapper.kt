package com.project.nagad.remote.mapper

interface Mapper<T, E> {

    fun fromNetworkToData(e: E): T

    fun toNetworkFromData(t: T): E
}