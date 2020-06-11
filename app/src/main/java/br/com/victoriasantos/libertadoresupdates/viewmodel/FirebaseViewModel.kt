package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.Location
import br.com.victoriasantos.libertadoresupdates.domain.Player
import br.com.victoriasantos.libertadoresupdates.domain.Profile
import br.com.victoriasantos.libertadoresupdates.interactor.FirebaseInteractor
import com.google.android.gms.maps.model.LatLng

class FirebaseViewModel(val app : Application) : AndroidViewModel(app) {

    private val interactor = FirebaseInteractor(app.applicationContext)

    fun acaoFirebaseUsuario(email: String, senha: String , tipoAcao : Int, callback: (result: String, id: Int) -> Unit) {

        interactor.acaoFirebaseUsuario(email, senha, tipoAcao){ result ->

            if(result == "EV"){
                callback(app.applicationContext.getString(R.string.email_required), 0)
            }
            else if(result == "SV"){
                callback(app.applicationContext.getString(R.string.senha_required), 0)
            }
            else if(result == "SC"){
                callback(app.applicationContext.getString(R.string.senha_tamanho_required), 0)
            }
            else if(result == "S" && tipoAcao == 1){
                callback(app.applicationContext.getString(R.string.welcome), 1)
            }
            else if(result == "S" && tipoAcao == 2){
                callback(app.applicationContext.getString(R.string.sucesso_cadastro_autenticado), 1)
            }
            else{
                callback(result, 0)
            }
        }

    }

    fun logout(callback: (result: String) -> Unit){
        interactor.logout(callback)
    }

    fun verifyLogin(callback: (result: String?) -> Unit){
        interactor.verifyLogin(callback)
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

    fun getMarkers(callback: (markers: Array<Location>?) -> Unit) {
        interactor.getMarkers(callback)
    }




}