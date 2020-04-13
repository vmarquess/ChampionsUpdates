package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.interactor.FirebaseInteractor

class FirebaseViewModel(val app : Application) : AndroidViewModel(app) {

    private val interactor = FirebaseInteractor(app.applicationContext)

    fun login(email: String, senha: String, callback: (result: String, id: Int) -> Unit) {

        interactor.login(email, senha){ result ->

            if(result == "EV"){
                callback("Email obrigatório", 0)
            }
            else if(result == "SV"){
                callback("Senha obrigatória", 0)
            }
            else if(result == "SC"){
                callback("Senha precisa ter ao menos 6 caracteres", 0)
            }
            else if(result == "S"){
                callback("Email autenticado, para concluir o cadastro preencha o perfil!", 1)
            }
            else{
                callback(result, 0)
            }


        }

    }
}