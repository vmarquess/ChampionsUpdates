package br.com.victoriasantos.libertadoresupdates.view.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.activities.matches.MatchesActivity
import br.com.victoriasantos.libertadoresupdates.view.activities.registerAndLogin.LoginActivity
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this). get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logout.setOnClickListener {
            logout()
        }

        mapa.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        chatbot.setOnClickListener {
            startActivity(Intent(this, ChatBotActivity::class.java))
        }

        times.setOnClickListener {
            startActivity(Intent(this, TeamsActivity::class.java))
        }

        grupos.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }

        derrotas.setOnClickListener {
            startActivity(Intent(this, MatchesActivity::class.java))
        }

        perfil.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    fun logout(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Deseja realmente sair?")
        builder.apply {
            setPositiveButton("SIM", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    viewModel.logout{
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            })
            setNegativeButton("NÃO", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                }
            })
        }
        builder.show()
    }




}
