package br.com.victoriasantos.libertadoresupdates.view.activities.matches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.JogosAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_jogos_anteriores.*

class JogosAnterioresActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogos_anteriores)

        configureRecyclerView()
        showMatches()
    }

    fun configureRecyclerView(){
        jogosAnt_Recycleview.layoutManager = LinearLayoutManager(this)
    }

    fun showMatches(){
        viewModel.lastMatches(530,20){ matches ->
            val adapter = JogosAdapter(matches)
            jogosAnt_Recycleview.adapter = adapter

        }
    }
}
