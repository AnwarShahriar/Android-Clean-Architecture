package com.project.nagad.data.mapper

interface Mapper<T, E> {

    fun fromDataToDomain(e: E): T

    fun toDataFromDomain(t: T): E
}