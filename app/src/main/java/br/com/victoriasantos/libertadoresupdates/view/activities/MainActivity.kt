package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        times.setOnClickListener {
            startActivity(Intent(this, TimesActivity::class.java))
            finish()
        }

        grupos.setOnClickListener {
            startActivity(Intent(this, GruposActivity::class.java))
        }

        jogos.setOnClickListener {
            startActivity(Intent(this, JogosActivity::class.java))
        }

        perfil.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }




}
