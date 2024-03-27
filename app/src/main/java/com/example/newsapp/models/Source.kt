package com.example.newsapp.models

//data class Source(
//    val id: String,
//    val name: String
//)
data class Source(
    val id: String?,
    val name: String?
) {
    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}
