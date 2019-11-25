package com.project.nagad.local.mapper

interface Mapper<T, E> {

    fun fromLocalToData(e: E): T

    fun toLocalFromData(t: T): E
}