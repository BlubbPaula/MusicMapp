package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class Results(
    val albummatches: Albummatches,
    @Json(name = "@attr")
    val attr: AttrResults,
    @Json(name = "opensearch:itemsPerPage")
    val opensearchItemsPerPage: String,
    @Json(name = "opensearch:Query")
    val opensearchQuery: OpensearchQuery,
    @Json(name = "opensearch:startIndex")
    val opensearchStartIndex: String,
    @Json(name = "opensearch:totalResults")
    val opensearchTotalResults: String
)