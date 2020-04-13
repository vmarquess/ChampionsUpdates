package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.Profile
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

    fun cadastro(email: String, senha: String, callback: (result: String, id: Int) -> Unit){
        interactor.cadastro(email, senha){ result ->

            if(result == "EV"){
                callback(app.applicationContext.getString(R.string.email_required), 0)
            }
            else if(result == "SV"){
                callback(app.applicationContext.getString(R.string.password_required), 0)
            }
            else if(result == "SC"){
                // TODO: MUDAR AS STRINGS PARA O PADRAO ACIMA
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

    fun changePassword(email: String, callback: (result: String, id: Int) -> Unit){
        interactor.changePassword(email){ result ->
            if(result == "EMPTY EMAIL"){
                callback("Digite o E-mail", 0)
            }
            else{
                callback("E-mail para recuperar senha enviado", 1)
            }
        }

    }

    fun getEmail(callback: (email: String) -> Unit){
        interactor.getEmail(callback)
    }

    fun consulta(callback: (perfil: Profile?) -> Unit){
        interactor.consulta(callback)
    }

    fun saveData(emailCampo: String, nome: String, telefone: String, time: String, callback: (result: String, id: Int) -> Unit){
        interactor.saveData(emailCampo, nome, telefone, time){ result ->
            if (result == "EMPTY DATA"){
                callback("Email deve estar preenchido!", 0)
            }
            else if(result == "SUCCESS"){
                callback("Perfil salvo!", 1)

            }
            else if(result == "UID RECOVER FAIL"){
                callback("Erro na recuperação da identificação do usuário", 0)
            }
            else{
                //error
                callback(result, 0)
            }
        }
    }




}