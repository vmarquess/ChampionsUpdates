package br.com.victoriasantos.libertadoresupdates.view.activities.registerAndLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.view.activities.MainActivity
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_register.bt_cadastro
import kotlinx.android.synthetic.main.activity_register.campoEmail
import kotlinx.android.synthetic.main.activity_register.campoSenha
import kotlinx.android.synthetic.main.activity_register.pBar


class RegisterActivity : AppCompatActivity() {

    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this). get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        pBar.visibility = GONE

        bt_cadastro.setOnClickListener { cadastrar()  }
    }


    private fun cadastrar(){
        pBar.visibility = VISIBLE
        val email = campoEmail.text.toString()
        val senha = campoSenha.text.toString()

        viewModel.acaoFirebaseUsuario(email, senha,2) { result, id ->
            pBar.visibility = GONE
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
            if(id == 1) {
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            }
        }

    }
}
