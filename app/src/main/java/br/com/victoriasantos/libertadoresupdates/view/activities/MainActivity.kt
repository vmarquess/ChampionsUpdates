package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.activities.matches.JogosActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        times.setOnClickListener {
            startActivity(Intent(this, TimesActivity::class.java))
        }

        grupos.setOnClickListener {
            startActivity(Intent(this, GruposActivity::class.java))
        }

        derrotas.setOnClickListener {
            startActivity(Intent(this, JogosActivity::class.java))
        }

        perfil.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }




}
