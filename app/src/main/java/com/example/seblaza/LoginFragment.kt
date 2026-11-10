package com.example.seblaza

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var btnToggleContrasena: ImageView
    private lateinit var ingresar: Button
    private var isPasswordVisible: Boolean = false

    companion object {
        private const val USUARIO_CORRECTO = "admin"
        private const val CONTRASENA_CORRECTA = "seblaza123"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupListeners()
    }

    private fun initViews(view: View) {
        usuario = view.findViewById(R.id.editTextUsuario)
        contrasena = view.findViewById(R.id.editTextContrasena)
        btnToggleContrasena = view.findViewById(R.id.btnToggleContrasena)
        ingresar = view.findViewById(R.id.btnIngresar)
    }

    private fun setupListeners() {
        ingresar.setOnClickListener {
            if (validarCampos()) {
                navegarASalario()
            }
        }

        btnToggleContrasena.setOnClickListener {
            togglePasswordVisibility()
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Ocultar contraseña
            contrasena.transformationMethod = PasswordTransformationMethod.getInstance()
            btnToggleContrasena.setImageResource(R.drawable.ic_eye_off)
            isPasswordVisible = false
        } else {
            // Mostrar contraseña
            contrasena.transformationMethod = HideReturnsTransformationMethod.getInstance()
            btnToggleContrasena.setImageResource(R.drawable.ic_eye)
            isPasswordVisible = true
        }
        // Mover el cursor al final del texto
        contrasena.setSelection(contrasena.text.length)
    }

    private fun validarCampos(): Boolean {
        val userText = usuario.text.toString().trim()
        val passText = contrasena.text.toString().trim()

        if (userText.isEmpty()) {
            usuario.error = "Por favor, ingrese su usuario"
            usuario.requestFocus()
            return false
        }

        if (passText.isEmpty()) {
            contrasena.error = "Por favor, ingrese su contraseña"
            contrasena.requestFocus()
            return false
        }

        if (userText != USUARIO_CORRECTO || passText != CONTRASENA_CORRECTA) {
            Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun navegarASalario() {
        Toast.makeText(requireContext(), "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
        limpiarTextos()
        val intent = Intent(requireContext(), SalarioActivity::class.java)
        startActivity(intent)
    }

    private fun limpiarTextos() {
        usuario.text.clear()
        contrasena.text.clear()
        usuario.error = null
        contrasena.error = null
        if (isPasswordVisible) {
            togglePasswordVisibility()
        }
    }
}