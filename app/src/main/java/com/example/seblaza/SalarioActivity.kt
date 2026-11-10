package com.example.seblaza

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class SalarioActivity : AppCompatActivity() {

    private lateinit var editEmpleado: EditText
    private lateinit var editHoras: EditText
    private lateinit var editTarifa: EditText
    private lateinit var btnProcesar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrarSesion: ImageView
    private lateinit var muestraEmpleado: TextView
    private lateinit var muestraHorasT: TextView
    private lateinit var muestraTarifa: TextView
    private lateinit var muestraSueldoBruto: TextView
    private lateinit var  muestraDes_esaalud: TextView
    private lateinit var muestraDes_afp: TextView
    private lateinit var muestraSueldoNeto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salario)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        editEmpleado = findViewById(R.id.editEmpleado)
        editHoras = findViewById(R.id.editHoras)
        editTarifa = findViewById(R.id.editTarifa)
        btnProcesar = findViewById(R.id.btnProcesar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrarSesion = findViewById(R.id.CerraSesion)
        muestraEmpleado = findViewById(R.id.MuestraEmpleado)
        muestraHorasT = findViewById(R.id.MuestraHoras)
        muestraTarifa = findViewById(R.id.MuestraTarifa)
        muestraSueldoBruto = findViewById(R.id.MuestraSueldoBruto)
        muestraDes_esaalud = findViewById(R.id.MuestraDescuentoEssalud)
        muestraDes_afp=findViewById(R.id.MuestraDescuentoAfp)
        muestraSueldoNeto=findViewById(R.id.MuestraSueldoNeto)

    }

    private fun setupListeners() {
        btnProcesar.setOnClickListener {
            // Capturar el texto de las cajas de texto en 3 variables
            val empleado: String = editEmpleado.text.toString().trim()
            val horasT: String = editHoras.text.toString().trim()
            val tarifaH: String = editTarifa.text.toString().trim()

            // Conversión segura de datos para evitar cierres inesperados (Crash)
            val horas = horasT.toIntOrNull() ?: 0
            val tarifa = tarifaH.toDoubleOrNull() ?: 0.0
            val sueldoBruto = horas * tarifa
            val desalud =(horas* tarifa)* 0.12
            val dafp= (horas * tarifa) * 0.10
            val sue_neto=(horas*tarifa)-(desalud+dafp)

            muestraEmpleado.text = empleado
            muestraHorasT.text = horas.toString()
            muestraTarifa.text = String.format(Locale.US, "$ %.2f", tarifa)
            muestraSueldoBruto.text = String.format(Locale.US, "$ %.2f", sueldoBruto)
            muestraDes_esaalud.text=String.format(Locale.US,"$ %.2f",desalud)
            muestraDes_afp.text=String.format(Locale.US,"$ %.2f",dafp)
            muestraSueldoNeto.text=String.format(Locale.US,"$ %.2f",sue_neto)
        }

        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        btnCerrarSesion.setOnClickListener {
            finish()
        }
    }

    private fun limpiarCampos() {
        editEmpleado.text.clear()
        editHoras.text.clear()
        editTarifa.text.clear()
        editEmpleado.error = null
        editHoras.error = null
        editTarifa.error = null
        editEmpleado.requestFocus()
        muestraEmpleado.text=""
        muestraTarifa.text=""
        muestraSueldoNeto.text=""
        muestraHorasT.text=""
        muestraDes_afp.text=""
        muestraDes_esaalud.text=""
        muestraSueldoBruto.text=""
    }
}