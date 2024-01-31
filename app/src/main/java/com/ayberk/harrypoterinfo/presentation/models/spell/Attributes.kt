package com.ayberk.harrypoterinfo.presentation.models.spell

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attributes(
    val category: String?,
    val creator: String?,
    val effect: String?,
    val hand: String?,
    val image: String?,
    val incantation: String?,
    val light: String?,
    val name: String?,
    val slug: String?,
    val wiki: String?
) : Parcelable