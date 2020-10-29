package com.example.roomdatabasepushnotification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
class Contact(val name: String, val number: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}