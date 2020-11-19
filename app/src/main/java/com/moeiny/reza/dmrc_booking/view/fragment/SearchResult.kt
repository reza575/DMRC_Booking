package com.moeiny.reza.dmrc_booking.view.fragments

import com.moeiny.reza.dmrc_booking.model.Node

interface SearchResult {
    fun onSuccess(node: Node)

    fun onFail()
}