package com.moeiny.reza.dmrc_booking.model

import org.json.JSONException
import org.json.JSONObject

class Node(station: JSONObject) {
    var id: Long = 0
    var name: String? = null
    var isExchange = false


    init {
        id = station.getLong("id")
        name = station.getString("name")
        isExchange = station.getBoolean("isExchange")
    }

   override fun toString(): String {
        return name!!
    }
}