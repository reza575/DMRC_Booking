package com.moeiny.reza.dmrc_booking.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.moeiny.reza.dmrc_booking.MainActivity

import com.moeiny.reza.dmrc_booking.R
import com.moeiny.reza.dmrc_booking.model.DataSource
import com.moeiny.reza.dmrc_booking.model.Node
import com.moeiny.reza.dmrc_booking.model.Ticket


class History : Fragment() {
    private lateinit var list: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_history, container, false)
        list= inflate.findViewById<ListView>(R.id.historyList)
        MainActivity.activity.viewModel.loadTicket(this::process)
        list.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var item:Ticket = list.adapter.getItem(position) as Ticket
                var startNode = DataSource.getNode(item.source.toLong())
                var destinationNode = DataSource.getNode(item.destination.toLong())
                if(startNode == null || destinationNode == null){
                    return;
                }
                val fm1 = MainActivity.activity.supportFragmentManager
                val fragmentTransaction = fm1.beginTransaction()
                var detailFragment = Details(startNode, destinationNode, object : SearchResult {
                    override fun onSuccess(node: Node) {
                    }

                    override fun onFail() {
                        val fm1 = MainActivity.activity.supportFragmentManager
                        val fragmentTransaction = fm1.beginTransaction()
                        fragmentTransaction.replace(R.id.mainFragment, this@History);
                        fragmentTransaction.commit();
                    }
                })
                fragmentTransaction.replace(R.id.mainFragment, detailFragment);
                fragmentTransaction.commit();
            }
        })
        return inflate
    }

    fun process(result: Int?, tickets: ArrayList<Ticket>): Int {
        if (result == 0 || result == -1) {
            var adapter =
                ArrayAdapter<Ticket>(context!!, android.R.layout.simple_list_item_1, tickets)
            list.adapter = adapter;
        } else if (result == 1) {
            // Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show()
        }
        return 0
    }

}
