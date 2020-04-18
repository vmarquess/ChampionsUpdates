package br.com.victoriasantos.libertadoresupdates.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.TabelaAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_grupos.*

class GruposActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this). get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grupos)

        configureRecyclerView()
        showTable()
    }

    private fun configureRecyclerView(){
         TabelaRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun showTable(){
        viewModel.table(530){ tabela ->
            val adapter = TabelaAdapter(tabela)
            TabelaRecyclerView.adapter = adapter
        }

    }
}
