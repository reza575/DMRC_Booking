package com.moeiny.reza.dmrc_booking.model

data class Ticket(
    val time: Long,
    var source: String,
    val destination: String
){
    var key:String? = ""
    constructor():this(0,"","")
}
