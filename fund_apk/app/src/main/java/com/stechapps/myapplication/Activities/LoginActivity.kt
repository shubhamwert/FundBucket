package com.stechapps.myapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.stechapps.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth=FirebaseAuth.getInstance()

        bt_login_button.setOnClickListener {

            when(sw_swtich_login.isChecked){
                false -> Login()
                true -> Signup()
                else -> Toast.makeText(applicationContext," something went wrong",Toast.LENGTH_SHORT).show()

            }

        }








    }

    private fun Signup() {
        mAuth.createUserWithEmailAndPassword(ed_login_username.text.toString(),ed__login_password.text.toString()).addOnSuccessListener {
            Toast.makeText(applicationContext," Signned up in successfully ",Toast.LENGTH_SHORT).show()

            Login()

        }.addOnFailureListener {
            Log.w("<><><><><SIGN UP<><><><><><>", "createUserWithEmail:failure$it")
            Toast.makeText(baseContext, "Authentication failed."+it.toString(),
                Toast.LENGTH_SHORT).show()

        }

    }

    fun Login(){
        mAuth.signInWithEmailAndPassword(ed_login_username.text.toString(),ed__login_password.text.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(applicationContext," Logged in successfully ",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,ProfileFunder::class.java))
                finish()

            }
            else{
                Toast.makeText(applicationContext," something went wrong",Toast.LENGTH_SHORT).show()
                Log.d("<><><><Login><><><><",""+it.exception.toString())

            }
        }

    }
}
