package com.droidbaza.navigationboostersample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExampleModel(
    val description: String = "same Description"
) : Parcelable

