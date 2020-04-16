package br.com.victoriasantos.libertadoresupdates.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor
import br.com.victoriasantos.libertadoresupdates.view.adapter.TimeAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_times.*

class TimesActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this). get(FootballAPIViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_times)

        configureRecyclerView()
        showTeams()
    }

    private fun showTeams(){

        viewModel.teams(1251) { times ->
            val adapter = TimeAdapter(times)
            TimesRecyclerView.adapter = adapter

        }

    }

    private fun configureRecyclerView() {
        TimesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
