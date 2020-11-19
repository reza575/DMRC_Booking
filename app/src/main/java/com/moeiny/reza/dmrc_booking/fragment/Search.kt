package com.moeiny.reza.dmrc_booking.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.moeiny.reza.dmrc_booking.*
import com.moeiny.reza.dmrc_booking.model.Node


class Search : Fragment() {

    var startNode: Node? = null;
    var destinationNode: Node? = null;

    var details: Button? = null;
    var register: Button? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_search, container, false)
        details = inflate.findViewById<Button>(R.id.detail)
        details!!.setOnClickListener {
            val fm1 = MainActivity.activity.supportFragmentManager
            val fragmentTransaction = fm1.beginTransaction()
            var detailFragment = Details(startNode, destinationNode, object : SearchResult {
                override fun onSuccess(node: Node) {
                }

                override fun onFail() {
                    val fm1 = MainActivity.activity.supportFragmentManager
                    val fragmentTransaction = fm1.beginTransaction()
                    fragmentTransaction.replace(R.id.mainFragment, this@Search);
                    fragmentTransaction.commit();
                }
            })
            fragmentTransaction.replace(R.id.mainFragment, detailFragment);
            fragmentTransaction.commit();
        }
        register = inflate.findViewById<Button>(R.id.register)
        register!!.setOnClickListener {

        }
        extraButtons()
        var hintStart = inflate.findViewById<TextView>(R.id.hint_start)
        if (startNode != null) {
            hintStart.text = startNode.toString();
        }
        var start = inflate.findViewById<Button>(R.id.start)
        start.setOnClickListener {
            val fm = MainActivity.activity.supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            var search = SearchBox("Select Start Station", object : SearchResult {
                override fun onSuccess(node: Node) {
                    startNode = node;
                    val fm1 = MainActivity.activity.supportFragmentManager
                    val fragmentTransaction = fm1.beginTransaction()
                    fragmentTransaction.replace(R.id.mainFragment, this@Search);
                    fragmentTransaction.commit();
                }

                override fun onFail() {
                    val fm1 = MainActivity.activity.supportFragmentManager
                    val fragmentTransaction = fm1.beginTransaction()
                    fragmentTransaction.replace(R.id.mainFragment, this@Search);
                    fragmentTransaction.commit();
                }
            })
            fragmentTransaction.replace(R.id.mainFragment, search);
            fragmentTransaction.commit();
        }

        var hintDestination = inflate.findViewById<TextView>(R.id.hint_end)
        if (destinationNode != null) {
            hintDestination.text = destinationNode.toString()
        }
        var destination = inflate.findViewById<Button>(R.id.destination)
        destination.setOnClickListener {
            val fm = MainActivity.activity.supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            var search = SearchBox("Select Destination", object : SearchResult {
                override fun onSuccess(node: Node) {
                    destinationNode = node;
                    val fm1 = MainActivity.activity.supportFragmentManager
                    val fragmentTransaction = fm1.beginTransaction()
                    fragmentTransaction.replace(R.id.mainFragment, this@Search);
                    fragmentTransaction.commit()
                }

                override fun onFail() {
                    val fm1 = MainActivity.activity.supportFragmentManager
                    val fragmentTransaction = fm1.beginTransaction()
                    fragmentTransaction.replace(R.id.mainFragment, this@Search);
                    fragmentTransaction.commit();
                }
            })
            fragmentTransaction.replace(R.id.mainFragment, search);
            fragmentTransaction.commit();
        }
        return inflate
    }

    fun extraButtons() {
        if (startNode == null || destinationNode == null) {
            return;
        }
        details!!.isEnabled = true;
        register!!.isEnabled = true;


     /*   */
    }

}
