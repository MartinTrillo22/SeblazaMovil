package com.example.seblaza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class ContactosFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactos, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomCompleto=view.findViewById<EditText>(R.id.txtNombre)
        val correo=view.findViewById<EditText>(R.id.txtCorreo)
        val celular=view.findViewById<EditText>(R.id.txtCelular)
        val asunto=view.findViewById<EditText>(R.id.txtAsunto)
        val mensaje=view.findViewById<EditText>(R.id.txtMensaje)
        val enviar=view.findViewById<Button>(R.id.btnEnviarMensaje)
        val limpiar=view.findViewById<Button>(R.id.btnLimpiar)

        val db= FirebaseFirestore.getInstance()
        val contexto=requireContext().applicationContext

        enviar.setOnClickListener {
            val nomCompletoTexto=nomCompleto.text.toString().trim()
            val correoTexto=correo.text.toString().trim()
            val celularTexto=celular.text.toString().trim()
            val asuntoTexto=asunto.text.toString().trim()
            val mensajeTexto=mensaje.text.toString().trim()

            if (nomCompletoTexto.isEmpty() || correoTexto.isEmpty()
                || celularTexto.isEmpty() || asuntoTexto.isEmpty() || mensajeTexto.isEmpty()){
                Toast.makeText(contexto,
                    "Completa correctamente los campos",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val datos= hashMapOf(
                "nombreCompleto" to nomCompletoTexto,
                "correo" to correoTexto,
                "celular" to celularTexto,
                "asunto" to asuntoTexto,
                "mensaje" to mensajeTexto
            )
            enviar.isEnabled=false

            //conexion
            db.collection("contactos")
                .add(datos)
                .addOnSuccessListener {
                    Toast.makeText(
                        contexto,
                        "Datos guardados correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    nomCompleto.text.clear()
                    correo.text.clear()
                    celular.text.clear()
                    asunto.text.clear()
                    mensaje.text.clear()
                    nomCompleto.requestFocus()
                }
                .addOnFailureListener { error ->
                    Toast.makeText(
                        contexto,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnCompleteListener {
                    enviar.isEnabled=true
                }


        }


        limpiar.setOnClickListener {
            nomCompleto.text.clear()
            correo.text.clear()
            celular.text.clear()
            correo.text.clear()
            asunto.text.clear()
            mensaje.text.clear()
            nomCompleto.requestFocus()
        }

    }

}