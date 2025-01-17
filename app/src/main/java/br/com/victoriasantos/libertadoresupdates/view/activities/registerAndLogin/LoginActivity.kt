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

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this).get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pBar.visibility = GONE
        verifyLogin()

        bt_cadastro.setOnClickListener { cadastrar() }
        esqueci_senha.setOnClickListener { esqueciSenha() }
        bt_login.setOnClickListener { login() }
    }

    fun verifyLogin() {
        viewModel.verifyLogin {result ->
            if(result != null) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun login() {
        pBar.visibility = VISIBLE
        val email = campoEmail.text.toString()
        val senha = campoSenha.text.toString()

        viewModel.acaoFirebaseUsuario(email, senha,1) { result, id ->
            pBar.visibility = GONE
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
            if (id == 1) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun cadastrar() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun esqueciSenha() {
        startActivity(Intent(this, PasswordRecoveryActivity::class.java))
    }
}