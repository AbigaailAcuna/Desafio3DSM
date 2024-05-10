package com.example.desafio3dsm.model


    data class Recurso(
        var id : Int ?= null,
        var titulo : String ?= null,
        var descripcion : String ?= null,
        var tipo : String ?= null,
        var enlace : String ?= null,
        var imagen : String ?= null)
//para pasar info a detalles:
    {
        companion object {
            var selectedRecurso: Recurso? = null
        }
    }
