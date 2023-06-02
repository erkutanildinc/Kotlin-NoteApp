package com.example.sqllite

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sqllite.models.Note

class NoteDetail : AppCompatActivity() {

    lateinit var noteTitleText: TextView
    lateinit var noteDetailText: TextView
    lateinit var noteDateText: TextView
    lateinit var deleteBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        MainActivity.db = DB(this)
        noteTitleText = findViewById(R.id.noteDetailTitleTextView)
        noteDetailText = findViewById(R.id.noteDetailDetailTextView)
        noteDateText = findViewById(R.id.noteDetailDateTextView)
        deleteBtn = findViewById(R.id.noteDetailDeleteBtn)

        var intent = getIntent()
        var title = intent.getStringExtra("title")
        var detail = intent.getStringExtra("detail")
        var date = intent.getStringExtra("date")
        var nid = intent.getStringExtra("nid")?.toInt()

        noteTitleText.text = title
        noteDetailText.text = detail
        noteDateText.text = date

        deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Note!")
            builder.setMessage("Are you sure you want to delete this note?")
            builder.setCancelable(true)

            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                MainActivity.db.deleteNote(nid = nid!!)
                finish()
                Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show()
            })

            builder.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
            })

            builder.show()
        }
    }

}