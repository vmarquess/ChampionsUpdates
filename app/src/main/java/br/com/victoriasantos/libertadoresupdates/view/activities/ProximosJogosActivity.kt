package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.ProximosJogosAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_proximos_jogos.*

class ProximosJogosActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximos_jogos)
        configureRecyclerView()
        showMatches()
    }


    fun configureRecyclerView() {
        jogosProximos_Recycleview.layoutManager = LinearLayoutManager(this)
    }

    fun showMatches() {
        viewModel.nextMatches(530,20) { Nextmatches, mensagem ->

            if(mensagem.isNullOrBlank()){
                val adapter = ProximosJogosAdapter(Nextmatches)
                jogosProximos_Recycleview.adapter = adapter
            }
            else{
                Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, JogosActivity::class.java))
                finish()
            }
        }
    }
}