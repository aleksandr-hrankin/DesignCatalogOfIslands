package ua.antibyte.designviewpagercatalog.data.model

import java.io.Serializable

data class IslandInfo(
    val name: String = "",
    val period: String = "",
    val departure: String = "",
    val resId: Int = 0
) : Serializable {
    companion object {
        const val KEY = "ISLAND_INFO_KEY"
    }
}
