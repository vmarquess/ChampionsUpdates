package br.com.victoriasantos.libertadoresupdates.view.activities.matches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.MatchesAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)
        pBar.visibility = GONE
        configureRecyclerView()
        showMatches(0)

        atualizarbtn.setOnClickListener {
            showMatches(1)
        }
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

    fun showMatches(update : Int){
        if(update == 1){
            JogosRecyclerView.adapter = null
        }
        pBar.visibility = VISIBLE
        viewModel.matches(530, update){ matches, date ->
            data.text = getString(R.string.last_update) + date
            val adapter = MatchesAdapter(matches)
            JogosRecyclerView.adapter = adapter
            pBar.visibility = GONE
        }
    }
}

