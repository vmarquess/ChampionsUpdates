package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.TeamAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_times.*

class TeamsActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this). get(FootballAPIViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_times)
        pBar.visibility = GONE

        configureRecyclerView()
        showTeams()
    }

    private fun showTeams(){
        pBar.visibility = VISIBLE
        viewModel.teams(530) { times ->
            val adapter = TeamAdapter(this, times)
            TimesRecyclerView.adapter = adapter
            pBar.visibility = GONE

        }

    }

    fun players(Id: String, nome: String){
        val intent = Intent(this, PlayersActivity::class.java)
        intent.putExtra("team_id", Id)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }

    private fun configureRecyclerView() {
        TimesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
