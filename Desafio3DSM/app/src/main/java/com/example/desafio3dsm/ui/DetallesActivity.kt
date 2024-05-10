package com.example.desafio3dsm.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio3dsm.MainActivity
import com.example.desafio3dsm.R
import com.example.desafio3dsm.model.Recurso
import com.squareup.picasso.Picasso

class DetallesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val tituloTextView = findViewById<TextView>(R.id.titulo)
        val descripcionTextView = findViewById<TextView>(R.id.descripcion)
        val tipoTextView = findViewById<TextView>(R.id.tipo)
        val enlaceTextView = findViewById<TextView>(R.id.enlace)
        val imagenImageView = findViewById<ImageView>(R.id.imagenImageView)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        val recurso = Recurso.selectedRecurso

        tituloTextView.text = recurso?.titulo
        descripcionTextView.text = recurso?.descripcion
        tipoTextView.text = recurso?.tipo
        enlaceTextView.text = recurso?.enlace

        // Crear un SpannableString para aplicar el subrayado al enlace
        val enlaceText = recurso?.enlace ?: ""
        val enlaceSpannable = SpannableString(enlaceText)
        enlaceSpannable.setSpan(UnderlineSpan(), 0, enlaceText.length, 0)
        enlaceTextView.text = enlaceSpannable

        recurso?.imagen?.let {
            Picasso.get()
                .load(it)
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
                .error(R.drawable.ic_launcher_background) // Error image if loading fails
                .into(imagenImageView)
        }

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        enlaceTextView.setOnClickListener {
            val url = recurso?.enlace
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

    }
}
