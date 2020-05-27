package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Location
import br.com.victoriasantos.libertadoresupdates.domain.Profile
import br.com.victoriasantos.libertadoresupdates.repository.FirebaseRepository

class FirebaseInteractor(private val context : Context) {

    private val repository = FirebaseRepository(context)
    private var profile: Profile? = null

    fun campoVazio(campo: String): Boolean {

        if(campo != null){
            if (campo.isEmpty()) {
                return true
            }
            else if (campo.isBlank()){
                return true
            }
        }else{
            return true
        }

        return false
    }

    fun acaoFirebaseUsuario(email: String, senha: String, tipoAcao : Int, callback: (result: String) -> Unit) {

        if(this.campoVazio(email)){
            callback("EV")
            return
        }
        if(this.campoVazio(senha)) {
            callback("SV")
            return

        } else if(senha.length < 6){
            callback("SC")
        }

        if(tipoAcao == 1){
            repository.login (email, senha, callback)
        }else if(tipoAcao == 2){
            repository.cadastro (email, senha, callback)
        }
    }

    fun changePassword(email: String, callback: (result: String) -> Unit){
        if(!this.campoVazio(email)){
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
                repository.updateEmail(email) { result ->
                    if (result == "S") {
                        if (!this.campoVazio(email)) {
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
                if (!this.campoVazio(email)) {
                    repository.saveData(email, nome, telefone, time, callback)
                } else {
                    callback("EMPTY DATA")
                    // Todos os campos devem ser preenchidos!"
                }
            }
        }
    }

    fun getMarkers(callback: (locations: Array<Location>?) -> Unit) {
        repository.getMarkers { snapshot ->
            val locations = mutableListOf<Location>()
            if (snapshot != null && snapshot.hasChildren() == true) {
                snapshot.children.forEach { l ->
                    val location = l.getValue(Location::class.java)
                    if (location != null) {
                        locations.add(location)
                    }
                }
                callback(locations.toTypedArray())
            } else {
                callback(null)
            }
        }
    }
}