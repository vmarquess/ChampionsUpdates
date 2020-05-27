package br.com.victoriasantos.libertadoresupdates.view.activities.matches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.MatchesAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_jogos.*

class MatchesActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogos)
        pBar.visibility = GONE
        configureRecyclerView()
        showMatches()

        jogosProx.setOnClickListener{
            startActivity(Intent(this, NextMatchesActivity::class.java))
            finish()
        }
        jogosPassados.setOnClickListener {
            startActivity(Intent(this, PastMatchesActivity::class.java))
        }
        jogosNow.setOnClickListener{
            startActivity(Intent(this, OnGoingMatchesActivity::class.java))
            finish()
        }
    }


    fun configureRecyclerView(){
        JogosRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun showMatches(){
        pBar.visibility = VISIBLE
        viewModel.matches(530){ matches ->
            val adapter = MatchesAdapter(matches)
            JogosRecyclerView.adapter = adapter
            pBar.visibility = GONE
        }
    }
}

