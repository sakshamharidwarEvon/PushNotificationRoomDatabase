package com.example.roomdatabasepushnotification

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    fun insert(contact: Contact?) {
        repository.insert(contact)
    }

    fun delete(contact: Contact?) {
        repository.delete(contact)
    }

    init {
        repository = ContactRepository(application)
        allContacts = ContactDao.allContact()
    }
}
