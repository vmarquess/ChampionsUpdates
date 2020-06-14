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
import br.com.victoriasantos.libertadoresupdates.view.adapter.OnGoingMatchesAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_ongoing_matches.*

class OnGoingMatchesActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy{
        ViewModelProvider(this).get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ongoing_matches)
        pBar.visibility = GONE

        configureRecyclerView()
        showCurrentMatches()
    }

   private fun configureRecyclerView(){
       JogosAcontecendoRecyclerView.layoutManager = LinearLayoutManager(this)
   }

    private fun showCurrentMatches(){
        pBar.visibility = VISIBLE
        viewModel.currentMatches(530){ m, mensagem ->
                if(mensagem.isNullOrBlank()){
                    val adapter = OnGoingMatchesAdapter(m)
                    JogosAcontecendoRecyclerView.adapter = adapter
                    pBar.visibility = GONE
                }
                else {
                    Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MatchesActivity::class.java))
                    pBar.visibility = GONE
                    finish()
                }
            }
        }
    }

