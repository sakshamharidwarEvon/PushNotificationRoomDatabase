package com.example.roomdatabasepushnotification

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    private class PopulateDbAsyncTask private constructor(db: ContactDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private val contactDao: ContactDao
        protected override fun doInBackground(vararg voids: Void): Void? {
            contactDao.insert(Contact("App Started 1", "9999977878"))
            contactDao.insert(Contact("App Started 2", "7555555999"))
            fillWithStartingData(activity)
            return null
        }

        init {
            contactDao = db!!.contactDao()
        }
    }

    companion object {
        private val contact: Contact? = null
        private var instance: ContactDatabase? = null
        private var activity: Context? = null
        @Synchronized
        fun getInstance(context: Context?): ContactDatabase? {
            activity = context!!.applicationContext
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java, "contact_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback: RoomDatabase.Callback = object : Callback() {
            fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }

        private fun fillWithStartingData(context: Context?) {
            val dao = getInstance(context)!!.contactDao()
            val contacts = loadJSONArray(context)
            try {
                for (i in 0 until contacts!!.length()) {
                    val contact = contacts.getJSONObject(i)
                    val contactName = contact.getString("name")
                    val phoneNumber = contact.getString("phone")
                    dao.insert(Contact(contactName, phoneNumber))
                }
            } catch (e: JSONException) {
            }
        }

        private fun loadJSONArray(context: Context?): JSONArray? {
            val builder = StringBuilder()
            val `in` = context!!.resources.openRawResource(R.raw.contact_list)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("contacts")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }
    }
}
