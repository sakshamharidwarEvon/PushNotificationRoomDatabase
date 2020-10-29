package com.example.roomdatabasepushnotification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder?>() {
    private var contacts: List<Contact> = ArrayList()
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ContactHolder(itemView)
    }

    fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val currContact = contacts[position]
        holder.name.setText(currContact.name)
        holder.number.setText(currContact.number)
    }

    fun getItemCount(): Int {
        return contacts
    }

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    internal inner class ContactHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView
        private val number: TextView

        init {
            name = itemView.findViewById(R.id.contactName)
            number = itemView.findViewById(R.id.contactNumber)
        }
    }
}
