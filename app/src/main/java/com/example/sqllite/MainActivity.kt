package com.example.sqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.sqllite.adapter.NoteAdapter
import com.example.sqllite.models.Note

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var db: DB
    }

    lateinit var noteListView : ListView
    lateinit var btnAddNote: Button
    lateinit var customNoteAdapter: NoteAdapter
    var noteList = mutableListOf<com.example.sqllite.models.Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddNote = findViewById(R.id.addNoteBtn)
        noteListView = findViewById(R.id.noteListView)
        db = DB(this)
        createNoteList()

        btnAddNote.setOnClickListener(addNoteBtnOnClickListener)

        noteListView.setOnItemClickListener{adapterView,view,position,id ->
            var noteDetailIntent = Intent(this,NoteDetail::class.java)
            noteDetailIntent.putExtra("title",noteList.get(position).title)
            noteDetailIntent.putExtra("detail",noteList.get(position).detail)
            noteDetailIntent.putExtra("date",noteList.get(position).date)
            noteDetailIntent.putExtra("nid",noteList.get(position).nid.toString())
            startActivity(noteDetailIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        db = DB(this)
        createNoteList()
    }

    val addNoteBtnOnClickListener = View.OnClickListener {
        var intentNoteAdd = Intent(this, NoteAddActivity::class.java)
        startActivity(intentNoteAdd)
    }


    fun createNoteList(){
        noteList = db.readAllNotes()
        customNoteAdapter = NoteAdapter(this,noteList)
        noteListView.adapter = customNoteAdapter
    }
}