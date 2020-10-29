package com.example.roomdatabasepushnotification

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert
    fun insert(contact: Contact?)

    @Delete
    fun delete(contact: Contact?)

    @get:Query("SELECT * FROM contact_table ")
    val allContact: LiveData<List<Contact?>?>?
}