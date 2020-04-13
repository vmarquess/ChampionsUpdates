package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this). get(FirebaseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

       consulta()
        bt_voltar.setOnClickListener {
            finish()
        }
    }


        private fun consulta() {

            viewModel.getEmail { email ->
                emailUsuario.setText(email)
            }
            viewModel.consulta { perfil ->
                // Mostra os valores que estão no banco de dados
                nomeUsuario.setText(perfil?.nome)
                telefoneUsuario.setText(perfil?.telefone)
                timeUsuario.setText(perfil?.time)

            }
            bt_salvar.setOnClickListener { saveData() }

        }

    private fun saveData() {
        val emailCampo = emailUsuario.text.toString()
        val nome = nomeUsuario.text.toString()
        val telefone = telefoneUsuario.text.toString()
        val time = timeUsuario.text.toString()

        viewModel.saveData(emailCampo, nome, telefone, time){ result, id ->
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
            if(id == 1){
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
        }

    }

}
