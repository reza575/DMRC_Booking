package com.moeiny.reza.dmrc_booking.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.moeiny.reza.dmrc_booking.DataSource
import com.moeiny.reza.dmrc_booking.Node

import com.moeiny.reza.dmrc_booking.R
import kotlinx.android.synthetic.main.fragment_search_box.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchBox.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchBox.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchBox(var title:String,var result: SearchResult) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_search_box, container, false)
        var searchTitle = inflate.findViewById<TextView>(R.id.searchTitle)
        searchTitle.setText(title)
        var canceal = inflate.findViewById<Button>(R.id.canceal)
        canceal.setOnClickListener{
            result.onFail()
        }
        var searchBox = inflate.findViewById<EditText>(R.id.searchBox)
        var searchList =  inflate.findViewById<ListView>(R.id.searchListBox)
        var adapter = ArrayAdapter<Node>(context!!, android.R.layout.simple_list_item_1, DataSource.nodes)
        searchList.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                    result.onSuccess(adapter.getItem(position)!!)
            }
        })
        searchList.adapter = adapter;
        searchBox.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.e("hamid", s.toString())
                adapter.getFilter().filter(s.toString());
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        /*searchBox.setOnTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.getFilter().filter(query);
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return false
            }

        })*/
        return inflate
    }
}
