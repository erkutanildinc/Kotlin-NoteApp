package com.example.sqllite.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sqllite.R
import com.example.sqllite.models.Note
import org.w3c.dom.Text

class NoteAdapter (private val context : Activity, private val list : MutableList<Note>) : ArrayAdapter<Note>(context,
    R.layout.note_list_item,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rootView = context.layoutInflater.inflate(R.layout.note_list_item,null,true)
        val noteItemTitle = rootView.findViewById<TextView>(R.id.noteItemTitleTextView)

        val note = list.get(position)

        noteItemTitle.text = note.title
        return rootView
    }
}
