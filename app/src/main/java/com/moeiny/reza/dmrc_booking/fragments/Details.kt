package com.moeiny.reza.dmrc_booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.moeiny.reza.dmrc_booking.DataSource
import com.moeiny.reza.dmrc_booking.GraphProcessor
import com.moeiny.reza.dmrc_booking.Node

import com.moeiny.reza.dmrc_booking.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Details(var start: Node?,var destination:Node? , var result: SearchResult) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var processor = GraphProcessor(DataSource.nodes, DataSource.arcs);
        var r = processor.process(start!!.id, destination!!.id)
        var sb = StringBuffer()
        var totalCost = 0
        for (n in r) {
            sb.append(n.name).append(", ")
            if (n.isExchange) {
                sb.append("5.00")
                totalCost += 5
            } else {
                sb.append("2.00")
                totalCost += 2
            }
            sb.append("\n")
        }
        sb.append("total cost = ").append(totalCost)
        val inflate = inflater.inflate(R.layout.fragment_details, container, false)
        var info = inflate.findViewById<TextView>(R.id.info)
        info.text = sb.toString()
        var close = inflate.findViewById<Button>(R.id.close)
        close.setOnClickListener {
            result.onFail()
        }
        return inflate
    }

}
