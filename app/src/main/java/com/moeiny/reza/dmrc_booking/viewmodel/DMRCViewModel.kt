package com.moeiny.reza.dmrc_booking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.moeiny.reza.dmrc_booking.model.Ticket


class DMRCViewModel : ViewModel() {

    //declare variables
    var ticketArray = ArrayList<Ticket>()
    var database = FirebaseDatabase.getInstance()

    //initialize view model
    init {
        Log.i("DMRC", "DMRC_booking created")
    }

    override fun onCleared() {
        super.onCleared()
    }


    //function to save notes on firebase Database
    fun saveTicket(ticket: Ticket) {
        val reference = database.reference
        reference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                reference.child("Tickets").push().setValue(ticket)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }



    //function to Loading saved Data from Database to Application
    var loadTickets = false
    @Synchronized
    fun loadTicket(process: (result: Int?, tickets: ArrayList<Ticket>) -> Int) {
        if (loadTickets) {
            process(-1, ticketArray)
            return
        }

        var tickets = database.getReference("Tickets")
        tickets.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                ticketArray.clear()
                for (postSnapshot in dataSnapshot.children) {
                    var ticket = postSnapshot.getValue(Ticket::class.java)
                    if (ticket == null)
                        continue;
                    ticket.key = postSnapshot.key
                    ticketArray.add(ticket)
                }
                process(0, ticketArray)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                process(1, ticketArray)
            }
        })
        loadTickets = true;
    }
}