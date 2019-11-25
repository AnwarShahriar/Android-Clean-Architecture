package com.project.nagad.data.repository.home

import com.project.nagad.data.model.AllHomeInfoData
import io.reactivex.Observable

interface HomeDataSourceRemote {

    fun getAllHomeInfo(): Observable<AllHomeInfoData>

}