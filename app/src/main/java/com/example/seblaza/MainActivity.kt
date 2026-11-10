package com.example.seblaza

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Configurar el ícono del menú (hamburguesa) en la barra superior
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Escuchar los clics en el menú
        navView.setNavigationItemSelectedListener(this)

        // Cargar el InicioFragment por defecto al abrir la app
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment()).commit()
            navView.setCheckedItem(R.id.nav_inicio)
        }
    }

    // Lógica para cambiar de Fragment según la opción seleccionada
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_inicio -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment()).commit()
            R.id.nav_nosotros -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NosotrosFragment()).commit()
            R.id.nav_contactos -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ContactosFragment()).commit()
            R.id.nav_login -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment()).commit()
        }
        // Cerrar el menú después de hacer clic
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Lógica para que el botón de hamburguesa reaccione al toque
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}