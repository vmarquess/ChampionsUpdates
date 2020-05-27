package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.activities.matches.MatchesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapa.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        chatbot.setOnClickListener {
            startActivity(Intent(this, ChatBotActivity::class.java))
        }

        times.setOnClickListener {
            startActivity(Intent(this, TeamsActivity::class.java))
        }

        grupos.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }

        derrotas.setOnClickListener {
            startActivity(Intent(this, MatchesActivity::class.java))
        }

        perfil.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }




}
