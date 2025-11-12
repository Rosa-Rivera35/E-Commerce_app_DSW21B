package com.example.tiendavirtualapp_kotlin.Cliente

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.tiendavirtualapp_kotlin.Cliente.Bottom_Nav_Fragments_Cliente.FragmentMisOrdenesC
import com.example.tiendavirtualapp_kotlin.Cliente.Bottom_Nav_Fragments_Cliente.FragmentTiendaC
import com.example.tiendavirtualapp_kotlin.Cliente.Nav_Fragments_Cliente.FragmentInicioC
import com.example.tiendavirtualapp_kotlin.Cliente.Nav_Fragments_Cliente.FragmentMiPerfilC
import com.example.tiendavirtualapp_kotlin.R
import com.example.tiendavirtualapp_kotlin.databinding.ActivityMainClienteBinding
import com.google.android.material.navigation.NavigationView

class MainActivityCliente : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(FragmentInicioC())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.navFragment, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.op_inicio_c -> {
                replaceFragment(FragmentInicioC())
            }
            R.id.op_mi_perfil_c -> {
                replaceFragment(FragmentMiPerfilC())
            }
            R.id.op_cerrar_session_c ->{
                Toast.makeText(applicationContext, "Has cerrado sesión", Toast.LENGTH_SHORT).show()
                finish()
            }
            R.id.op_tienda_c ->{
                replaceFragment(FragmentTiendaC())
            }
            R.id.op_mis_ordenes_c ->{
                replaceFragment(FragmentMisOrdenesC())
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}