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

data class AttrResults(
    @Json(name = "for")
    val forX: String
)

data class OpensearchQuery(
    val role: String,
    val searchTerms: String,
    val startPage: String,
    @Json(name = "#text")
    val text: String
)

data class Albummatches(
    val album: List<Albummatch>
)

data class Albummatch(
    val artist: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)