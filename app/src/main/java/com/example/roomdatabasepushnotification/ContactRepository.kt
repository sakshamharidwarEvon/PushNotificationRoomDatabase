package com.example.roomdatabasepushnotification

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ContactRepository(application: Application?) {
    private val contactDao: ContactDao
    val allContacts: LiveData<List<Contact>>

    fun insert(contact: Contact?) {
        InsertContactAsyncTask(contactDao).execute(contact)
    }

    fun delete(contact: Contact?) {
        DeleteContactAsyncTask(contactDao).execute(contact)
    }

    private class InsertContactAsyncTask(private val contactDao: ContactDao) :
        AsyncTask<Contact?, Void?, Void?>() {
        protected override fun doInBackground(vararg contacts: Contact): Void? {
            contactDao.insert(contacts[0])
            return null
        }
    }

    private class DeleteContactAsyncTask(private val contactDao: ContactDao) :
        AsyncTask<Contact?, Void?, Void?>() {
        protected override fun doInBackground(vararg contacts: Contact): Void? {
            contactDao.delete(contacts[0])
            return null
        }
    }

    init {
        val database: ContactDatabase = ContactDatabase.getInstance(application)
        contactDao = database.contactDao()
        allContacts = contactDao.allContact
    }
}
