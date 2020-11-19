package com.moeiny.reza.dmrc_booking.model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Ticket(
    val time: Long,
    val source: String,
    val destination: String
) {
    var key: String? = ""

    constructor() : this(0, "", "")

    override fun toString(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        val strDate: String = dateFormat.format(Date(time))
        return String.format(
            "%s ----> %s (%s )",
            DataSource.getName(source.toLong()),
            DataSource.getName(destination.toLong()),
            strDate
        )
    }
}
