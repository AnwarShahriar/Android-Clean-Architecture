package com.project.nagad.bazaar.constants


object ApiConstants {

    //API BASE url
    val BASE_URL = devServerUrl

    //Media Base URL
    val MEDIA_BASE_URL = mediaBaseUrl

    private val productionServerUrl: String
        get() = "https://www.gameof11.com/"

    private val devServerUrl: String
        get() = "http://demo4237859.mockable.io/"

    private val mediaBaseUrl: String
        get() = "http://media.gameof11.com/"

}
