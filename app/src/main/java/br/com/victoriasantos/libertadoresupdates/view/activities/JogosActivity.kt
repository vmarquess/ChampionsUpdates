package br.com.victoriasantos.libertadoresupdates.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel

class JogosActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogos)
        configureRecyclerView()
        showMatches()
    }


    fun configureRecyclerView(){

    }

    fun showMatches(){
        viewModel.matches{ m ->

        }

    }




}

