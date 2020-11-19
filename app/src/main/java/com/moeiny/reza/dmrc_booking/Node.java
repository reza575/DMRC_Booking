/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moeiny.reza.dmrc_booking;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Node {

    public long id;
    public String name;
    public boolean isExchange;

    public Node(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node(JSONObject station) throws JSONException {
        this.id = station.getLong("id");
        this.name = station.getString("name");
        this.isExchange = station.getBoolean("isExchange");
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
