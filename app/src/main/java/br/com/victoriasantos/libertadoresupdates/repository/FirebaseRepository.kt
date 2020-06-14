package br.com.victoriasantos.libertadoresupdates.repository

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRepository(context: Context){

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private var profile: Profile? = null


    fun cadastro(email: String, senha: String, callback: (result: String) -> Unit){
        val operation = mAuth.createUserWithEmailAndPassword(email, senha)
        operation.addOnCompleteListener { task ->
            if(task.isSuccessful){
                callback("S")
            }
            else{
                val error = task.exception?.localizedMessage
                    ?: "Não foi possível entrar no aplicativo no momento"
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

    fun logout(callback: (result: String) -> Unit){
        mAuth.signOut()
        callback("S")
    }

    fun verifyLogin(callback: (result: FirebaseUser?) -> Unit){
        mAuth.addAuthStateListener { user ->
            callback(user.currentUser)
        }
    }


    fun changePassword(email: String){
        mAuth.sendPasswordResetEmail(email)
    }

    fun getEmail(callback: (email: String?) -> Unit){
        val emailFinal = mAuth.currentUser?.email
        callback(emailFinal)
    }

    fun consulta(callback: (snapshot: DataSnapshot?) -> Unit){
        val email = mAuth.currentUser?.email
        val profiles = database.getReference("Usuários")
        val query = profiles.orderByChild("email").equalTo(email)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback(null)
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                callback(snapshot)
            }
        })

    }


    fun updateEmail(email: String, callback: (result: String) -> Unit ){

        val usuario = mAuth.currentUser

        usuario?.updateEmail(email)?.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                val error = task.exception?.localizedMessage
                    ?: "Houve um erro ao atualizar o e-mail!"
                callback(error)
            } else {
                callback("S")
            }
        }
    }

    fun saveData(email: String, nome: String, telefone: String, time: String, callback: (result: String, profile : Profile?) -> Unit){
        profile = Profile(
            email = email,
            nome = nome,
            telefone = telefone,
            time = time
        )

        val uid = mAuth.currentUser?.uid

        if(uid != null){
            // Variável que define qual nó será atualizado, nesse caso será o nó "Usuários"
            val userprofile = database.getReference("Usuários/$uid") // $uid é o onde será substituido pelo id do usuário logado
            userprofile.setValue(profile) //Atualiza/cria os dados
            callback("SUCCESS", profile)

        }
        else {
            callback("UID RECOVER FAIL",null)
        }

    }

    fun getMarkers(callback: (snapshot: DataSnapshot?) -> Unit) {

        val ref = database.getReference("Location")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback(null)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                callback(snapshot)
            }
        })
    }


}