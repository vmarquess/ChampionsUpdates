package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this). get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        bt_cadastro.setOnClickListener { cadastrar()  }
    }


    private fun cadastrar(){

        val email = campoEmail.text.toString()
        val senha = campoEmail.text.toString()

        viewModel.acaoFirebaseUsuario(email, senha,2) { result, id ->

            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
            if(id == 1) {
                startActivity(Intent(this@CadastroActivity, MainActivity::class.java))
                finish()
            }
        }

    }
}
