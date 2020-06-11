package br.com.victoriasantos.libertadoresupdates.view.activities.matches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.NextMatchesAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_next_matches.*
import kotlinx.android.synthetic.main.activity_next_matches.pBar

class NextMatchesActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_matches)
        pBar.visibility = GONE
        atualizarb.setOnClickListener {
            showMatches(1)
        }
        configureRecyclerView()
        showMatches(0)
    }


    fun configureRecyclerView() {
        jogosProximos_Recycleview.layoutManager = LinearLayoutManager(this)
    }

    fun showMatches(update : Int) {
        if(update == 1){
            jogosProximos_Recycleview.adapter = null
        }
        pBar.visibility = VISIBLE
        viewModel.nextMatches(530,20, update) { Nextmatches, mensagem, date ->
            data.text = date
            if(mensagem.isNullOrBlank()){
                val adapter = NextMatchesAdapter(Nextmatches)
                jogosProximos_Recycleview.adapter = adapter
                pBar.visibility = GONE
            }
            else{
                Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MatchesActivity::class.java))
                pBar.visibility = GONE
                finish()
            }
        }
    }
}