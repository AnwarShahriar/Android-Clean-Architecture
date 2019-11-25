package com.project.nagad.domain.usercases.user

import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.domain.qualifiers.Background
import com.project.nagad.domain.qualifiers.Foreground
import com.project.nagad.domain.repository.user.UserRepository
import com.project.nagad.domain.usercases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<UserInfoEntity, GetUserInfoUseCase.Params>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun generateObservable(input: Params?): Observable<UserInfoEntity> {

        if (input == null) {
            throw IllegalArgumentException("User Identifier can't be null")
        }
        return userRepository.getUserInfo(input.identifier)
    }

    data class Params(val identifier: String)
}