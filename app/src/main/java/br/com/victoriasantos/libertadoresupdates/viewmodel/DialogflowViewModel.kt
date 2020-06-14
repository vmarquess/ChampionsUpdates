package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.interactor.DialogFlowInteractor

class DialogFlowViewModel(val app: Application) : AndroidViewModel(app) {
    val interactor = DialogFlowInteractor(app.applicationContext)
    fun sendTextMessage(text: String, sessionId: String, callback: (response: String?) -> Unit) {
        interactor.sendTextMessage(text, sessionId, callback)
    }
}