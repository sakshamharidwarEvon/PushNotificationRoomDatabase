package com.example.roomdatabasepushnotification

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var contactViewModel: ContactViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setHasFixedSize(true)
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, ClearActivity::class.java))
            Toast.makeText(applicationContext, "Database Removed", Toast.LENGTH_LONG).show()
        }
        val adapter = ContactAdapter()
        recyclerView.setAdapter(adapter)
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAllContacts().observe(this,
            Observer<List<Contact?>?> { contacts -> adapter.setContacts(contacts) })
    }
}