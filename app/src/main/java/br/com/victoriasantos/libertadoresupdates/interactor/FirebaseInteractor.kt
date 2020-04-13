package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Profile
import br.com.victoriasantos.libertadoresupdates.repository.FirebaseRepository

class FirebaseInteractor(private val context : Context) {

    private val repository = FirebaseRepository(context)
    private var profile: Profile? = null

    fun login(email: String, senha: String, callback: (result: String) -> Unit) {

        if (email.isEmpty()) {
            callback("EV")
            return
        }
        if (senha.isEmpty()) {
            callback("SV")
            return
        } else {
            if (senha.length < 6) {
                callback("SC")
                return
            }
        }
        repository.login(email, senha, callback)
    }

    fun cadastro(email: String, senha: String, callback: (result: String) -> Unit) {

        if (email.isEmpty()) {
            callback("EV")
            return
        }
        if (senha.isEmpty()) {
            callback("SV")
            return
        } else {
            if (senha.length < 6) {
                callback("SC")
                return
            }
        }
        repository.cadastro(email, senha, callback)
    }

    fun changePassword(email: String, callback: (result: String) -> Unit){
        if(email != ""){
            repository.changePassword(email)
            callback("EMAIL SENT")
        }
        else{
            callback("EMPTY EMAIL")
        }
    }

    fun getEmail(callback: (email: String) -> Unit){
        repository.getEmail {email ->
            if(email != null){
                callback(email)
            }
        }
    }

    fun consulta(callback: (perfil: Profile?) -> Unit) {
        repository.consulta { snapshot ->
            if (snapshot != null && snapshot.hasChildren() == true) {
                profile = snapshot.children.first().getValue(Profile::class.java)
                if (profile != null) {
                    callback(profile)
                } else {
                    callback(null)
                }
            } else {
                callback(null)
            }
        }
    }


    fun saveData(emailCampo: String, nome: String, telefone: String, time: String, callback: (result: String) -> Unit) {

        repository.getEmail { emailFinal ->
            if (!emailFinal.equals(emailCampo)) { // Pergunta se o email do usuario antigo(emailFinal) é diferente(por ter uma ! no início) ao email que está no campo da página(emailCampo)
                val email = emailCampo
                repository.UpdateEmail(email) { result ->
                    if (result == "S") {
                        if (email.isNotEmpty()) {
                            repository.saveData(email, nome, telefone, time, callback)
                        } else {
                            callback("EMPTY DATA")
                            // Todos os campos devem ser preenchidos!"
                        }

                    }
                    else{
                        callback(result)
                    }

                }
            }
            else{
                val email = emailCampo
                if (email.isNotEmpty()) {
                    repository.saveData(email, nome, telefone, time, callback)
                } else {
                    callback("EMPTY DATA")
                    // Todos os campos devem ser preenchidos!"
                }
            }
        }
    }
}