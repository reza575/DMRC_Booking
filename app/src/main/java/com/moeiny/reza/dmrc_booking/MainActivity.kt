package com.moeiny.reza.dmrc_booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moeiny.reza.dmrc_booking.fragments.Search

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
