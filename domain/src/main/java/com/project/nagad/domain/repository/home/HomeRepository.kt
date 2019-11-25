package com.project.nagad.domain.repository.home

import com.project.nagad.domain.entities.AllHomeInfoEntity
import io.reactivex.Observable

interface HomeRepository {

    fun getAllHomePageInfo(): Observable<AllHomeInfoEntity>

}