package br.com.victoriasantos.libertadoresupdates.view.activities.matches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.MatchesAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_past_matches.*
import kotlinx.android.synthetic.main.activity_past_matches.pBar

class PastMatchesActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_matches)
        pBar.visibility = GONE
        atualizarbt.setOnClickListener {
            showMatches(1)
        }
        configureRecyclerView()
        showMatches(0)


    }

    fun configureRecyclerView(){
        jogosAnt_Recycleview.layoutManager = LinearLayoutManager(this)
    }

    fun showMatches(update : Int){
        if(update == 1){
            jogosAnt_Recycleview.adapter = null
        }
        pBar.visibility = VISIBLE
        viewModel.lastMatches(530,20, update){ matches, date ->
            data.text = date
            val adapter = MatchesAdapter(matches)
            jogosAnt_Recycleview.adapter = adapter
            pBar.visibility = GONE

        }
    }
}
