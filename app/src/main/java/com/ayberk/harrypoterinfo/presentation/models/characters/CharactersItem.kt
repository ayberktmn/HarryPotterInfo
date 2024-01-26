package com.ayberk.harrypoterinfo.presentation.models.characters

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersItem(
    @SerializedName("actor")
    val actor: String?,
    @SerializedName("alive")
    val alive: Boolean?,
    @SerializedName("alternate_names")
    val alternateNames: List<String?>?,
    @SerializedName("ancestry")
    val ancestry: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean?,
    @SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean?,
    @SerializedName("house")
    val house: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("patronus")
    val patronus: String?,
    @SerializedName("species")
    val species: String?,
    val dateOfBirth : String?,
    @SerializedName("wizard")
    val wizard: Boolean?,
) : Parcelable