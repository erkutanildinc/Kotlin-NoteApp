package com.example.sqllite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqllite.models.Note

class DB(context: Context) : SQLiteOpenHelper(context, DBName, null, Version) {

    companion object {
        private val DBName = "NOTE"
        private val Version = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val noteTable = "CREATE TABLE \"NOTE\" (\n" +
                "\t\"nid\"\tINTEGER,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"detail\"\tTEXT,\n" +
                "\t\"date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nid\" AUTOINCREMENT)\n" +
                ");"

        if (p0 != null) {
            p0.execSQL(noteTable)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val noteTableDrop = "DROP TABLE IF EXISTS NOTE"
        p0?.execSQL(noteTableDrop)
        onCreate(p0)
    }

    fun addNote( title : String, detail : String, date: String) : Long{
        val db = this.writableDatabase  //insert yapıcaz bu bi yazma işlemi yani writable kullanıyoruz.
        val values = ContentValues()
        values.put("title",title)
        values.put("detail",detail)
        values.put("date",date)

        val affectedRow =  db.insert("NOTE", null, values)
        db.close()
        return affectedRow
    }

    fun readAllNotes() : MutableList<Note>{
        val db = this.readableDatabase
        var noteArr = mutableListOf<Note>()
        val cursor = db.query("NOTE",null,null,null,null,null,null)
        while(cursor.moveToNext()){
            val nid= cursor.getInt(0)
            val title = cursor.getString(1)
            val detail = cursor.getString(2)
            val date = cursor.getString(3)
            var note = Note(nid,title, detail, date)
            noteArr.add(note)
        }
        db.close()
        return noteArr
    }

    fun deleteNote(nid:Int) {
        val db = this.writableDatabase
        val status = db.delete("NOTE","nid=${nid}",null)
        db.close()
    }

    fun updateNote(title:String,detail:String,nid : Int){
        val db = writableDatabase
        val content = ContentValues()
        content.put("title",title)
        content.put("detail",detail)
        val status = db.update("NOTE",content,"nid=${nid}",null)
        db.close()
    }


}