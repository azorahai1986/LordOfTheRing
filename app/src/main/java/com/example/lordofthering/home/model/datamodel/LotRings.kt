package com.example.lordofthering.home.model.datamodel

data class LotRings(
    val docs: List<Doc>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)