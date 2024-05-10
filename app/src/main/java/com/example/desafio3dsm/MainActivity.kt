package com.example.desafio3dsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3dsm.adapter.RecursoAdapter
import com.example.desafio3dsm.model.Recurso
import com.example.desafio3dsm.ui.DetallesActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), RecursoAdapter.OnItemClickListener {
    private lateinit var dbref : DatabaseReference
    private lateinit var recursoRecyclerview : RecyclerView
    private lateinit var recursoArrayList : ArrayList<Recurso>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recursoRecyclerview = findViewById(R.id.recursoList)
        recursoRecyclerview.layoutManager = LinearLayoutManager(this)
        recursoRecyclerview.setHasFixedSize(true)

        recursoArrayList = arrayListOf()
        getRecursoData()
    }

    private fun getRecursoData() {
        dbref = FirebaseDatabase.getInstance().getReference("recursos")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (recursoSnapshot in snapshot.children){
                        val user = recursoSnapshot.getValue(Recurso::class.java)
                        recursoArrayList.add(user!!)
                    }
                    recursoRecyclerview.adapter = RecursoAdapter(recursoArrayList, this@MainActivity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun onItemClick(item: Recurso) {
        Recurso.selectedRecurso = item
        startActivity(Intent(this, DetallesActivity::class.java))
    }


}