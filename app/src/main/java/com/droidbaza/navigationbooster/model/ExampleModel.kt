package com.droidbaza.navigationbooster.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExampleModel(
    val description: String = "same Description"
) : Parcelable

