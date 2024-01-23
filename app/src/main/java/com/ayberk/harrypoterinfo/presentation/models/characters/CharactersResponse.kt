package com.ayberk.harrypoterinfo.presentation.models.characters


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("characters") var characters: List<Characters>? = null,
    @SerializedName("success") var success: Int?,
)