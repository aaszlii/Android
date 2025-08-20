package com.example.licznikpunktow

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "shooting_sessions.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "sessions"
        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "date"
        const val COLUMN_COMPETITION = "competition"
        const val COLUMN_POINTS = "points"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DATE TEXT,
                $COLUMN_COMPETITION TEXT,
                $COLUMN_POINTS INTEGER
            );
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertSession(date: String, competition: String, points: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATE, date)
            put(COLUMN_COMPETITION, competition)
            put(COLUMN_POINTS, points)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun deleteSession(id: Int): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun getAllSessions(): List<Session> {
        val list = mutableListOf<Session>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY id DESC", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
                val competition = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMPETITION))
                val points = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_POINTS))
                list.add(Session(id, date, competition, points))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return list
    }

}
