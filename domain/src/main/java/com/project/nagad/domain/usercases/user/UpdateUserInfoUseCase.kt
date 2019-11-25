package com.project.nagad.domain.usercases.user

import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.domain.qualifiers.Background
import com.project.nagad.domain.qualifiers.Foreground
import com.project.nagad.domain.repository.user.UserRepository
import com.project.nagad.domain.usercases.base.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foreGroundScheduler: Scheduler
) : CompletableUseCase<UpdateUserInfoUseCase.Params>(
    backgroundScheduler,
    foreGroundScheduler
) {
    override fun generateCompletable(input: Params?): Completable {
        if (input == null){
            throw IllegalArgumentException("UpdaterUserInfoTask can't be null")
        }

        return userRepository.updateUserInfo(input.userInfoEntity)

    }

    data class Params(val userInfoEntity: UserInfoEntity)
}