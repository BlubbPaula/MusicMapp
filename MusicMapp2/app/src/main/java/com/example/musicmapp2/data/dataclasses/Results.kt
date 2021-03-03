package com.example.musicmapp2.data.dataclasses


import com.google.gson.annotations.SerializedName

data class Results(
    val albummatches: Albummatches,
    @SerializedName("@attr")
    val attr: AttrResults,
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String,
    @SerializedName("opensearch:Query")
    val opensearchQuery: OpensearchQuery,
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String,
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String
)