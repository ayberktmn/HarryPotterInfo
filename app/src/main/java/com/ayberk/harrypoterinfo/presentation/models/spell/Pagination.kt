package com.ayberk.harrypoterinfo.presentation.models.spell

data class Pagination(
    val current: Int,
    val last: Int,
    val next: Int,
    val records: Int
)