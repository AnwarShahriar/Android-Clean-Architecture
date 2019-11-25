package com.project.nagad.domain.usercases.home

import com.project.nagad.domain.entities.AllHomeInfoEntity
import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.domain.qualifiers.Background
import com.project.nagad.domain.qualifiers.Foreground
import com.project.nagad.domain.repository.home.HomeRepository
import com.project.nagad.domain.repository.user.UserRepository
import com.project.nagad.domain.usercases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetAllHomeInfoUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<AllHomeInfoEntity, GetAllHomeInfoUseCase.Params>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun generateObservable(input: Params?): Observable<AllHomeInfoEntity> {
        if (input == null) {
            throw IllegalArgumentException("User Identifier can't be null")
        }
        return homeRepository.getAllHomePageInfo()
    }

    data class Params(val identifier: String)
}