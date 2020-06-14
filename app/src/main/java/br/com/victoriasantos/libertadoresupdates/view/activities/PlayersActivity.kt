package br.com.victoriasantos.libertadoresupdates.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_next_matches.*
import kotlinx.android.synthetic.main.activity_next_matches.pBar
import kotlinx.android.synthetic.main.activity_players.*

class PlayersActivity : AppCompatActivity() {


    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this). get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        pBar.visibility = GONE

        val id = intent.getStringExtra("team_id")
        val nome = intent.getStringExtra("nome")

        showPlayers(id, nome)
    }

    fun showPlayers(id: String, nome: String){
        pBar.visibility = VISIBLE
        viewModel.showPlayers(id, "2019-2020"){ players ->
            players
            if (players != null) {
                var str = ""
                players.forEach { p ->
                    str = str.plus(" ${p.name} ${p.position} ${p.age} ${p.nationality}" )
                }
                pBar.visibility = GONE
                header.text = nome
                playersTextView.text = str

            }
            else{
                pBar.visibility = GONE
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
            }
        }

    }


}
