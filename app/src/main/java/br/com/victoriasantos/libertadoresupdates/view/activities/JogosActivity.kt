package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.JogosAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_jogos.*

class JogosActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogos)
        configureRecyclerView()
        showMatches()

        jogosProx.setOnClickListener{
            startActivity(Intent(this, ProximosJogosActivity::class.java))
        }
    }


    fun configureRecyclerView(){
        JogosRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun showMatches(){
        viewModel.matches(530){ matches ->
            val adapter = JogosAdapter(matches)
            JogosRecyclerView.adapter = adapter

        }

    }




}

