package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this).get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_cadastro.setOnClickListener { cadastrar() }
        esqueci_senha.setOnClickListener { esqueciSenha() }

        bt_login.setOnClickListener { login() }
    }

    private fun login() {
        val email = campoEmail.text.toString()
        val senha = campoSenha.text.toString()

        viewModel.login(email, senha) { result, id ->
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()

            if (id == 1) {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
        }
    }

    private fun cadastrar() {
        startActivity(Intent(this, CadastroActivity::class.java))
    }

    private fun esqueciSenha() {
        startActivity(Intent(this, RecuperacaoSenhaActivity::class.java))
    }
}