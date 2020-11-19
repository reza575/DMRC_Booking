package com.moeiny.reza.dmrc_booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.moeiny.reza.dmrc_booking.model.DataSource
import com.moeiny.reza.dmrc_booking.view.fragments.History
import com.moeiny.reza.dmrc_booking.view.fragments.Search
import com.moeiny.reza.dmrc_booking.viewmodel.DMRCViewModel

class MainActivity : AppCompatActivity() {
    public lateinit var viewModel: DMRCViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var historyButton = findViewById<TextView>(R.id.historyButton)
        historyButton.setOnClickListener {
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            var history = History()
            fragmentTransaction.replace(R.id.mainFragment, history);
            fragmentTransaction.commit();
        }
        var searchButton = findViewById<TextView>(R.id.searchButton)
        searchButton.setOnClickListener {
            val fm = supportFragmentManager
            val fragmentTransaction = fm.beginTransaction()
            var search = Search()
            fragmentTransaction.replace(R.id.mainFragment, search);
            fragmentTransaction.commit();
        }
        viewModel = ViewModelProviders.of(this).get(DMRCViewModel::class.java)
        activity = this
        DataSource.load()
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        var search = Search()
        fragmentTransaction.replace(R.id.mainFragment, search);
        fragmentTransaction.commit();
    }
    companion object{
        lateinit var activity : MainActivity
    }
}
