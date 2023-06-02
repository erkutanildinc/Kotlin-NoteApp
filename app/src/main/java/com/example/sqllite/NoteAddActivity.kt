package com.example.sqllite

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import java.util.*

class NoteAddActivity : AppCompatActivity() {

    var selectDate: String? = null
    lateinit var btnDate: ImageButton
    lateinit var btnSave: Button
    lateinit var titleEditText : EditText
    lateinit var detailEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)

        btnSave = findViewById(R.id.saveBtn)
        btnDate = findViewById(R.id.dateBtn)
        titleEditText = findViewById(R.id.noteTitleEditText)
        detailEditText = findViewById(R.id.noteDetailEditText)
        MainActivity.db = DB(this)

        btnDate.setOnClickListener(dateBtnOnClickListener)
        btnSave.setOnClickListener(saveBtnOnClickListener)

    }


    val dateBtnOnClickListener = View.OnClickListener {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                var month = "${i2 + 1}"
                if (i2 + 1 < 10) {
                    month = "0${i2 + 1}"
                }
                selectDate = "$i3.$month.$i"

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    val saveBtnOnClickListener = View.OnClickListener {

        var title = titleEditText.text.toString()
        var detail = detailEditText.text.toString()
        if(title !=null || detail!=null){
            if(selectDate==null){
                val calendar = Calendar.getInstance()
                selectDate = "${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH)+1}.${calendar.get(Calendar.YEAR)}"
            }
            var status = MainActivity.db.addNote(title,detail, selectDate!!)
        }
        titleEditText.text.clear()
        detailEditText.text.clear()
        finish()
    }

}