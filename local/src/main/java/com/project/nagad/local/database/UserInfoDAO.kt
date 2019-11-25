package com.project.nagad.local.database

import androidx.room.*
import com.project.nagad.local.model.UserInfoLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserInfo(userInfoLocal: UserInfoLocal)

    @Query("SELECT * FROM user_info WHERE user_id=:identifier")
    fun getUserInfo(identifier: String): Observable<UserInfoLocal>

    @Query("DELETE FROM user_info")
    fun deleteUserInfo(): Completable

    @Update
    fun updateUserInfo(userInfoLocal: UserInfoLocal): Completable
}