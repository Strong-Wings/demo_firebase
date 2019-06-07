package com.example.dp_demo

import kotlin.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.sql.DatabaseMetaData


class MainActivity : AppCompatActivity() {
    val logTag = "DEMO_TAG"

    lateinit var btnAdd:Button
    lateinit var btnRead:Button
    lateinit var etName:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        btnRead = findViewById(R.id.btnRead)
        etName = findViewById(R.id.etName)

        btnAdd.setOnClickListener{
            saveData()
        }
    }

    private fun saveData(){
        val name = etName.text.toString()


        val ref = FirebaseDatabase.getInstance().getReference("person")
        val id = ref.push().key
        if (id == null) return
        val person = Person(id, name)
        ref.child(id).setValue(person).addOnCompleteListener {
            Toast.makeText(applicationContext, "Added", Toast.LENGTH_SHORT)
        }
    }
}
