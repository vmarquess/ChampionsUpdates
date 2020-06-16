package br.com.victoriasantos.libertadoresupdates.view.activities

import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.DialogFlowViewModel
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import kotlinx.android.synthetic.main.activity_chat_bot.*

class ChatBotActivity : AppCompatActivity() {

    val SessionId = (0..999999999999).random().toString()

    private val viewModel: DialogFlowViewModel by lazy {
        ViewModelProvider(this).get(DialogFlowViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        val human = ChatUser(1, getString(R.string.you), BitmapFactory.decodeResource(resources, R.drawable.ic_account_circle))

        val agent = ChatUser(2, "ChampionsUpdates's Bot", BitmapFactory.decodeResource(resources, R.drawable.bot_profile))

        my_chat_view.setInputTextHint("Digite 'Olá' para iniciar conversa.")
        my_chat_view.setRightBubbleColor(Color.BLUE)
        my_chat_view.setBackgroundDrawable(getDrawable(R.drawable.mainbackground))
        my_chat_view.setMessageMaxWidth(700)

        my_chat_view.setOnClickSendButtonListener(
            View.OnClickListener {
                my_chat_view.send(Message.Builder().setUser(human).setText(my_chat_view.inputText).build())
                val text = my_chat_view.inputText
                my_chat_view.inputText =""
                my_chat_view.setInputTextHint("Mensagem")

                viewModel.sendTextMessage(text, SessionId) { response ->
                    if(response.isNullOrEmpty()){
                        Toast.makeText(this,getString(R.string.conection_error), Toast.LENGTH_LONG).show()
                    }
                    else {
                        my_chat_view.send(Message.Builder().setRight(true).setUser(agent).setText(response).build()
                        )
                    }
                }
            }
        )
    }
}

