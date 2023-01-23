package com.droidbaza.navigationboostersample.navigation

sealed class Destination(
    val description: String = ""
) {
    object First : Destination("put multiply args and send to second screen and handle back args from third screen")
    object Second : Destination("get args from first screen and run empty third screen")
    object Third : Destination("handle back args from four screen  and put back args with pop to first screen")
    object Four : Destination("put back args to previous third screen")

    open val route: String = this.javaClass.name
    open val keyId
        get() = "keyId$route"
    open val keyItem
        get() = "keyItem$route"
    open val keyItems
        get() = "keyItems$route"
    open val keyBack
        get() = "keyBack$route"

}