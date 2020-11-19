package com.moeiny.reza.dmrc_booking.fragments

import com.moeiny.reza.dmrc_booking.Node

interface SearchResult {
    fun onSuccess(node:Node)

    fun onFail()
}