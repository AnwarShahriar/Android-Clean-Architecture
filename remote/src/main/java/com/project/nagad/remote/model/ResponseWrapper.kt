package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName

class ResponseWrapper(

    @SerializedName("user_info") val userInfoNetwork: UserInfoNetwork,
    @SerializedName("all_home_info") val allHomeInfoNetwork: AllHomeInfoNetwork

)
