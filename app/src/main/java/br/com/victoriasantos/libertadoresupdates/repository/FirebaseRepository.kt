package br.com.victoriasantos.libertadoresupdates.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository(context: Context){

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()


    fun cadastrar(email: String, senha: String, callback: (result: String) -> Unit){
        val operation = mAuth.createUserWithEmailAndPassword(email,senha)

        operation.addOnCompleteListener { task ->
            if(task.isSuccessful){
                callback("S")
            }
            else{
                val error = task.exception?.localizedMessage
                    ?: "Não foi possível cadastrar o usuário"

                callback(error)
            }
        }
    }

    fun login(email : String, senha : String, callback: (result : String) -> Unit){

        val operation = mAuth.signInWithEmailAndPassword(email, senha)
        operation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback("S")
            } else {
                val error = task.exception?.localizedMessage
                    ?: "Não foi possivel entrar o aplicativo"
                callback(error)
            }
        }
    }


}