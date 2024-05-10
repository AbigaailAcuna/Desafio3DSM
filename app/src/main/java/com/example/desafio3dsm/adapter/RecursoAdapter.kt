package com.example.desafio3dsm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3dsm.R
import com.example.desafio3dsm.model.Recurso
import com.squareup.picasso.Picasso

class RecursoAdapter(private val userList : ArrayList<Recurso>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecursoAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Recurso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recurso_item,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.titulo.text = currentitem.titulo
        holder.descripcion.text = currentitem.descripcion
        holder.tipo.text = currentitem.tipo
        holder.enlace.text = currentitem.enlace

        Picasso.get()
            .load(currentitem.imagen)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imagen)

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentitem)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titulo : TextView = itemView.findViewById(R.id.titulo)
        val descripcion : TextView = itemView.findViewById(R.id.descripcion)
        val imagen : ImageView = itemView.findViewById(R.id.imagenImageView)
        val tipo : TextView = itemView.findViewById(R.id.tipo)
        val enlace : TextView = itemView.findViewById(R.id.enlace)
    }
}