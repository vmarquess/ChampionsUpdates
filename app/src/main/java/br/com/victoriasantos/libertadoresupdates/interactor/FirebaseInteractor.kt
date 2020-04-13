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
}