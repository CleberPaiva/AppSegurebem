package com.cleberpaiva.segurebem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        text_tela_cadastro.setOnClickListener {
            var intent = Intent(this,FormCadastro::class.java)
            startActivity(intent)
        }

        bt_entrar.setOnClickListener {
            AutenticarUsuario()
        }
    }

    private fun AutenticarUsuario(){

        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        if (email.isEmpty() || senha.isEmpty()){
            var snackbar = Snackbar.make(layout_login, "Coloque um e-mail e uma senha!",Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", View.OnClickListener {

                })
            snackbar.show()
        }else{

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {

                if (it.isSuccessful){
                    AbrirTelaPrincipal()

                }


            }.addOnFailureListener {

                var snackbar = Snackbar.make(layout_login, "Erro de login!",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", View.OnClickListener {

                    })
                snackbar.show()
            }
        }
    }

    private fun AbrirTelaPrincipal(){

        var intent = Intent(this,TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }
}