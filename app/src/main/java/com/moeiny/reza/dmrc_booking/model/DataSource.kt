package com.moeiny.reza.dmrc_booking.model

import com.moeiny.reza.dmrc_booking.MainActivity
import com.moeiny.reza.dmrc_booking.R
import org.apache.commons.io.IOUtils
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class DataSource {
    companion object {

        var nodes =  ArrayList<Node>()
        var arcs =   HashMap<Long, Long>()

        fun load() {
            val `is` =
                MainActivity.activity.resources.openRawResource(
                    R.raw.map1)
            try {
                val jsonStr = IOUtils.toString(
                    `is`,
                    StandardCharsets.UTF_8)
                val json = JSONObject(jsonStr)
                val stations = json.getJSONArray("stations")
                val links = json.getJSONArray("links")
                for (i in 0 until stations.length()) {
                    val station:JSONObject = stations.getJSONObject(i)
                    nodes.add(Node(station))
                }
                for (i in 0 until links.length()) {
                    val link = links.getJSONObject(i)
                    //Log.e("", link.toString());
                    arcs[link.getLong("start")] = link.getLong("end")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}