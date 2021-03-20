package com.cleberpaiva.segurebem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_form_cadastro.*

class FormCadastro : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar!!.hide()

        bt_cadastrar.setOnClickListener {
            CadastrarUsuario()
        }


    }

    private fun CadastrarUsuario(){

        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        if(email.isEmpty() || senha.isEmpty()){
            //Toast.makeText(this,"Coloque o seu e-mail e sua senha!", Toast.LENGTH_SHORT).show()
            var snackbar = Snackbar.make(layout_cadastro, "Coloque seu e-mail e sua senha!",Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", View.OnClickListener {

                })
            snackbar.show()

        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {

                if (it.isSuccessful){
                    Toast.makeText(this,"Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        VoltarParaFormLogin()
                }
            }.addOnFailureListener {

                Toast.makeText(this,"Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun VoltarParaFormLogin(){

        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}