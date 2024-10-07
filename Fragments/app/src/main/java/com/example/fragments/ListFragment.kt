package com.example.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListFragment : Fragment() {

    private lateinit var listView: ListView
    private var listener: OnItemSelectedListener? = null

    private val items = listOf("Batman Begins", "The Prestige", "The Dark Knight", "Inception")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listView)
        listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            listener?.onItemSelected(position)
        }
        return view
    }

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }
}
