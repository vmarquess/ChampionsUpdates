package br.com.victoriasantos.libertadoresupdates.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.adapter.TableAdapter
import br.com.victoriasantos.libertadoresupdates.viewmodel.FootballAPIViewModel
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : AppCompatActivity() {

    private val viewModel: FootballAPIViewModel by lazy {
        ViewModelProvider(this). get(FootballAPIViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        pBar.visibility = GONE

        configureRecyclerView()
        showTable()
    }

    private fun configureRecyclerView(){
         TabelaRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun showTable(){
        pBar.visibility = VISIBLE
        viewModel.table(530){ tabela ->
            val adapter = TableAdapter(tabela)
            TabelaRecyclerView.adapter = adapter
            pBar.visibility = GONE
        }

    }
}
