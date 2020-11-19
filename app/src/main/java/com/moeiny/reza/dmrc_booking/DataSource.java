package com.moeiny.reza.dmrc_booking;

import android.util.Log;

import com.google.gson.JsonObject;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSource {
    public static ArrayList<Node> nodes = new ArrayList<>();
    public static HashMap<Long, Long> arcs = new HashMap<>();
    public static void load(){
        InputStream is = MainActivity.activity.getResources().openRawResource(R.raw.map1);
        try {
            String jsonStr = IOUtils.toString(is, StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(jsonStr);
            JSONArray stations = json.getJSONArray("stations");
            JSONArray links = json.getJSONArray("links");
            for(int i = 0; i<stations.length(); i++){
                JSONObject station = stations.getJSONObject(i);
                nodes.add(new Node(station));
            }
            for(int i = 0; i<links.length(); i++){
                JSONObject link = links.getJSONObject(i);
                //Log.e("", link.toString());
                arcs.put(link.getLong("start"),link.getLong("end"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

