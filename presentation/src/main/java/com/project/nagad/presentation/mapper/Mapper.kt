package com.project.nagad.presentation.mapper

interface Mapper<T, E> {

    fun fromViewToDomain(e: E): T

    fun toViewFromDomain(t: T): E

}
