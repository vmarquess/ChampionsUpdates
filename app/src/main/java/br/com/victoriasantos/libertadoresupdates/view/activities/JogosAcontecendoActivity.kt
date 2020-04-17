package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.EventosAdapter
import br.com.victoriasantos.libertadoresupdates.view.adapter.JogosAcontecendoAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_jogos_acontecendo.*
import kotlinx.android.synthetic.main.jogos_acontendo_item.*

class JogosAcontecendoActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogos_acontecendo)

        configureRecyclerView()
        showCurrentMatches()
    }

   private fun configureRecyclerView(){
       JogosAcontecendoRecyclerView.layoutManager = LinearLayoutManager(this)
       EventosRecyclerView.layoutManager = LinearLayoutManager(this)
   }

    private fun showCurrentMatches(){
        viewModel.currentMatches(530){ m, mensagem ->
                if(mensagem.isNullOrBlank()){
                    val adapter = JogosAcontecendoAdapter(m)
                    JogosAcontecendoRecyclerView.adapter = adapter
                }
                else {
                    Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, JogosActivity::class.java))
                    finish()
                }

            }

        }



    }

