package br.com.victoriasantos.libertadoresupdates.view.activities.registerAndLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_password_recovery.*

class PasswordRecoveryActivity : AppCompatActivity() {


    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this). get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_recovery)

        bt_enviar.setOnClickListener { alterarSenha() }

    }

    private fun alterarSenha(){
        val email = campoEmail.text.toString()
        viewModel.changePassword(email) { result, id ->

            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
            if(id == 1){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }
}
